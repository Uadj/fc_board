package com.example.fastcampusboard.dto;

import com.example.fastcampusboard.domain.Hashtag;

import java.io.Serializable;
import java.time.LocalDateTime;

public record HashtagDto(Long id, LocalDateTime createdAt, String createdBy,
                         LocalDateTime modifiedAt, String modifiedBy,
                         String hashtagName) implements Serializable {
    public static HashtagDto from(Hashtag entity) {
        return new HashtagDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.getHashtagName()
        );
    }
}
