package com.example.fastcampusboard.domain;

import com.example.fastcampusboard.dto.ArticleDto;
import com.example.fastcampusboard.dto.HashtagDto;
import com.example.fastcampusboard.dto.UserAccountDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

public record ArticleRequest(
        String title,
        String content
) {

    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return toDto(userAccountDto, null);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto, Set<HashtagDto> hashtagDtos) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtagDtos
        );
    }

}