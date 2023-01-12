package com.example.fastcampusboard.dto;

import com.example.fastcampusboard.dto.HashtagDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDto(LocalDateTime createdAt, String createdBy,
                         LocalDateTime modifiedAt, String modifiedBy, Long id,
                         String title, String content,
                         Set<HashtagDto> hashtags){
    public static ArticleDto of(String title, String content, Set<HashtagDto> hashtagDtos) {
        return new ArticleDto(null, null, null, null, null, title, content, hashtagDtos);
    }
}
