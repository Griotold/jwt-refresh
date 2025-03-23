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
@Table(name = "m_post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    public static Post create(String content) {
        return Post.builder().content(content).build();
    }
}
