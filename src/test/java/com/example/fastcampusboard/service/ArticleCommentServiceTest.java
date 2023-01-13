package com.example.fastcampusboard.service;

import com.example.fastcampusboard.domain.Article;
import com.example.fastcampusboard.dto.response.ArticleCommentResponse;
import com.example.fastcampusboard.repository.ArticleCommentRepository;
import com.example.fastcampusboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
public class ArticleCommentServiceTest {
    @InjectMocks
    private ArticleCommentService articleCommentService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 ID로 조회하면 댓글 리스트를 반환")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnArticleComments(){
        Long articleId = 1L;
        //given
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of(null, "title", "content")));
        //when
        List<ArticleCommentResponse> articleCommentResponseDtos = articleCommentService.searchArticleComments(articleId);
        //then
        assertThat(articleCommentResponseDtos).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

}
