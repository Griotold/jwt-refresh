package com.griotold.jwt_refresh.domain.repository;

import com.griotold.jwt_refresh.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
