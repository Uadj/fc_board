package com.example.fastcampusboard.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

public record HashtagDto(LocalDateTime createdAt, String createdBy,
                         LocalDateTime modifiedAt, String modifiedBy, Long id,
                         String hashtagName) implements Serializable {
}
