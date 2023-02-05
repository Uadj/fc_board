package com.example.fastcampusboard.service;

import com.example.fastcampusboard.domain.UserAccount;
import com.example.fastcampusboard.repository.ArticleRepository;
import com.example.fastcampusboard.repository.HashtagRepository;
import com.example.fastcampusboard.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;

@DataJpaTest
@DisplayName("비즈니스 로직 - 유저")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserAccountService userAccountService;


    @Autowired
    private TestEntityManager testEntityManager;

    EntityManager em;

    @BeforeEach
    void init(){
        em = testEntityManager.getEntityManager();
    }

    @Mock
    private HashtagService hashtagService;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private HashtagRepository hashtagRepository;

    @Test
    @DisplayName("계정 생성")
    public void saveTest(){
        String UserId = "ABCD";
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(UserId);
        userAccount.setUserPassword("acsd");
        userAccount.setEmail("dsdsd@naver.com");
        userAccount.setMemo("sdd");
        userAccount.setNickname("sdasd");
        em.persist(userAccount);
    }
}
