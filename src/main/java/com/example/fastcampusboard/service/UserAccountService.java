package com.example.fastcampusboard.service;

import com.example.fastcampusboard.domain.UserAccount;
import com.example.fastcampusboard.dto.UserAccountDto;
import com.example.fastcampusboard.dto.request.UserForm;
import com.example.fastcampusboard.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String username, String password, String email, String nickname, String memo) {
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(username, password, email, nickname, memo, username))
        );
    }
    public UserAccount saveUser(UserForm userForm){
        UserAccount userAccount = userForm.toEntity();
        return userAccountRepository.save(userAccount);
    }

}
