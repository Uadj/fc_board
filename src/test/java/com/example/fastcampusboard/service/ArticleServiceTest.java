package com.example.fastcampusboard.service;

import com.example.fastcampusboard.domain.Article;
import com.example.fastcampusboard.dto.ArticleWithCommentsDto;
import com.example.fastcampusboard.type.SearchType;
import com.example.fastcampusboard.dto.ArticleDto;
import com.example.fastcampusboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글을 조회하면 게시글을 반환")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnArticle(){
        //given

        //when
        ArticleWithCommentsDto articleWithCommentsDto = articleService.searchArticle(1L);
        //then
        assertThat(articleWithCommentsDto).isNotNull();
    }
    @DisplayName("게시글을 검색하면 게시글 리스트를 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnArticleList(){
        //given
        Pageable pageable = Pageable.ofSize(20);
        //when
        Page<ArticleDto> articles = articleService.searchArticles(SearchType.TITLE, "search keyword", pageable);
        //then
        assertThat(articles).isNotNull();
    }
    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveArticle(){
        //given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        //when
        articleService.saveArticle(ArticleDto.of(1L, "title", "content", null), null);
        //then
        then(articleRepository).should().save(any(Article.class));
    }
    @DisplayName("게시글 ID와 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAndInfo_whenUpdatingArticle_thenUpdateArticle(){
        //given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        //when
        articleService.updateArticle(1L, ArticleDto.of(1L, "title", "content", null));
        //then
        then(articleRepository).should().save(any(Article.class));
    }
    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle(){
        //given
        willDoNothing().given(articleRepository).delete(any(Article.class));
        //when
        articleService.deleteArticle(1L);
        //then
        then(articleRepository).should().delete(any(Article.class));
    }
}
