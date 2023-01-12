package com.example.fastcampusboard.service;

import com.example.fastcampusboard.type.SearchType;
import com.example.fastcampusboard.dto.ArticleDto;
import com.example.fastcampusboard.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        ArticleDto articleDto = articleService.searchArticle(1L);
        //then
        assertThat(articleDto);
    }
    @DisplayName("게시글을 검색하면 게시글 리스트를 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnArticleList(){
        //given

        //when
        Page<ArticleDto> articles = articleService.searchArticles(SearchType.TITLE, "search keyword");
        //then
        assertThat(articles).isNotNull();
    }
    @DisplayName("게시글을 조회하면 게시글을 반환")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnPage(){
        //given

        //when
        ArticleDto articleDto = articleService.searchArticle(1L);
        //then
        assertThat(articleDto);
    }
    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveArticle(){
        //given
        given(articleRepository.save(any))
        ArticleDto dto = ArticleDto.of("Uno", "title",  null);
        //when

        //then
    }
}
