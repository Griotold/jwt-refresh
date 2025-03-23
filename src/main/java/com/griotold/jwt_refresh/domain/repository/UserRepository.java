package com.griotold.jwt_refresh.domain.repository;

import com.griotold.jwt_refresh.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    Optional<User> findBySocialId(String socialId);
}
