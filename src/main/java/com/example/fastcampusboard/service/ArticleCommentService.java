package com.example.fastcampusboard.service;

import com.example.fastcampusboard.dto.response.ArticleCommentResponse;
import com.example.fastcampusboard.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;

    public List<ArticleCommentResponse> searchArticleComments(Long articleId) {
        return Collections.emptyList();
    }
}
