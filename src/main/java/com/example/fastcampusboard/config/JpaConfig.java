package com.example.fastcampusboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("uno"); // TODO : 스프링 시큐리티로 인증 기능을 사용할 떄 수정
    }
}
