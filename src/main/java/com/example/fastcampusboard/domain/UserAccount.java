package com.example.fastcampusboard.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@Builder
@AllArgsConstructor
@Setter
public class UserAccount extends AuditingFields {

    @Id
    @Column(length = 50, unique = true)
    @Setter
    private String userId;

    @Setter
    @Column(nullable = false) private String userPassword;

    @Setter @Column(length = 100, unique = true) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter private String memo;


    public UserAccount() {}

    public UserAccount(String userId, String userPassword, String email, String nickname, String memo, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return UserAccount.of(userId, userPassword, email, nickname, memo, null);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo, String createdBy) {
        return new UserAccount(userId, userPassword, email, nickname, memo, createdBy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }

}
