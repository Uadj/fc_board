package com.example.fastcampusboard.service;

import com.example.fastcampusboard.domain.Article;
import com.example.fastcampusboard.domain.Hashtag;
import com.example.fastcampusboard.domain.UserAccount;
import com.example.fastcampusboard.dto.ArticleWithCommentsDto;
import com.example.fastcampusboard.repository.ArticleRepository;
import com.example.fastcampusboard.repository.HashtagRepository;
import com.example.fastcampusboard.repository.UserAccountRepository;
import com.example.fastcampusboard.type.SearchType;
import com.example.fastcampusboard.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final HashtagService hashtagService;
    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;
    private final HashtagRepository hashtagRepository;
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String search_keyword, Pageable pageable) {
        if(search_keyword == null || search_keyword.isBlank()){
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(search_keyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(search_keyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(search_keyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(search_keyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtagNames(
                            Arrays.stream(search_keyword.split(" ")).toList(),
                            pageable
                    )
                    .map(ArticleDto::from);
        };
    }
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }
    public long getArticleCount() {
        return articleRepository.count();
    }
    public void saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
        Set<Hashtag> hashtags = renewHashtagsFromContent(dto.content());

        Article article = dto.toEntity(userAccount);
        article.addHashtags(hashtags);
        articleRepository.save(article);
    }

    public void updateArticle(Long id, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null) { article.setTitle(dto.title()); }
            if (dto.content() != null) { article.setContent(dto.content()); }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    private Set<Hashtag> renewHashtagsFromContent(String content) {
        Set<String> hashtagNamesInContent = hashtagService.parseHashtagNames(content);
        Set<Hashtag> hashtags = hashtagService.findHashtagsByNames(hashtagNamesInContent);
        Set<String> existingHashtagNames = hashtags.stream()
                .map(Hashtag::getHashtagName)
                .collect(Collectors.toUnmodifiableSet());

        hashtagNamesInContent.forEach(newHashtagName -> {
            if (!existingHashtagNames.contains(newHashtagName)) {
                hashtags.add(Hashtag.of(newHashtagName));
            }
        });

        return hashtags;
    }

}
