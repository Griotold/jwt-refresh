package com.griotold.jwt_refresh.domain.entity;

import com.griotold.jwt_refresh.domain.enums.Provider;
import com.griotold.jwt_refresh.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "m_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private Provider provider;

//    @Column(name = "email", nullable = false, unique = true)
    @Column(name = "email")
    private String email;

//    @Column(name = "nickname", nullable = false, unique = true)
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "child_name")
    private String childName;

    @Column(name = "child_age_group")
    private String childAgeGroup;

    public static User create(String socialId, Provider provider, String email, String nickname, Role role) {
        return User.builder()
                .socialId(socialId)
                .provider(provider)
                .email(email)
                .nickname(nickname)
                .role(role)
                .build();
    }
}
