package com.example.fastcampusboard.repository;

import com.example.fastcampusboard.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ArticleRepositoryCustom {
    Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable);

}
