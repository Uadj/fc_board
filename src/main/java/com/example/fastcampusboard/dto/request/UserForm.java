package com.example.fastcampusboard.dto.request;

import com.example.fastcampusboard.domain.UserAccount;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class UserForm {
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private String memo;

    public UserAccount toEntity(){
        UserAccount userAccount = UserAccount.builder()
                .userId(userId)
                .userPassword(password)
                .email(email)
                .nickname(nickname)
                .memo(memo)
                .build();
        return userAccount;
    }
}
