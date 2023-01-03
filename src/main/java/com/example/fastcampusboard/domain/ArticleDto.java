package com.example.fastcampusboard.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDto(LocalDateTime createdAt, String createdBy,
                         LocalDateTime modifiedAt, String modifiedBy, Long id,
                         String title, String content,
                         Set<HashtagDto> hashtags) implements Serializable {
}
