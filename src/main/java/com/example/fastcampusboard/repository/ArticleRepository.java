package com.example.fastcampusboard.repository;

import com.example.fastcampusboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
