package com.example.fastcampusboard.repository;

import com.example.fastcampusboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}