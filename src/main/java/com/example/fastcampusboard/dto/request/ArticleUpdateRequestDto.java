package com.example.fastcampusboard.dto.request;

import com.example.fastcampusboard.dto.HashtagDto;

import java.io.Serializable;
import java.util.Set;

public record ArticleUpdateRequestDto(String title, String content,
                                      Set<HashtagDto> hashtags) implements Serializable {
    public static ArticleUpdateRequestDto of(String title, String content,
                                             Set<HashtagDto> hashtags){
        return new ArticleUpdateRequestDto(title, content, hashtags);
    }
}
