package com.example.fastcampusboard.domain;

import com.example.fastcampusboard.dto.HashtagDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

public record ArticleRequest(String title, String content){
    public static ArticleRequest of(String title, String content){
        return new ArticleRequest(title, content);
    }
}
