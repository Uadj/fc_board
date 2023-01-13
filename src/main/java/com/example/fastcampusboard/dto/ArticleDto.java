package com.example.fastcampusboard.dto;

import com.example.fastcampusboard.domain.Article;
import com.example.fastcampusboard.domain.UserAccount;
import com.example.fastcampusboard.dto.HashtagDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleDto(Long id, LocalDateTime createdAt, String createdBy,
                         LocalDateTime modifiedAt, String modifiedBy,
                         String title, String content,
                         Set<HashtagDto> hashtags){
    public static ArticleDto of(Long id, String title, String content, Set<HashtagDto> hashtagDtos) {
        return new ArticleDto(null, null, null, null, null, title, content, hashtagDtos);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
        entity.getId(),
        entity.getCreatedAt(),
        entity.getCreatedBy(),
        entity.getModifiedAt(),
        entity.getModifiedBy(),
        entity.getTitle(),
        entity.getContent(),
        entity.getHashtags().stream().
                map(HashtagDto::from).
                collect(Collectors.toUnmodifiableSet())
                );
    }
    public Article toEntity(UserAccount userAccount){
        return Article.of(
                userAccount,
                title,
                content
        );
    }
}
