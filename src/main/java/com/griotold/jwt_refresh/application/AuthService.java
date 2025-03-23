package com.griotold.jwt_refresh.application;


import com.griotold.jwt_refresh.config.JwtService;
import com.griotold.jwt_refresh.domain.entity.User;
import com.griotold.jwt_refresh.domain.enums.Provider;
import com.griotold.jwt_refresh.domain.enums.Role;
import com.griotold.jwt_refresh.domain.repository.UserRepository;
import com.griotold.jwt_refresh.presentation.AuthRequest;
import com.griotold.jwt_refresh.presentation.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse authenticate(AuthRequest authRequest) {
        Optional<User> optionalUser = userRepository.findBySocialId(authRequest.socialId());
        User user;

        if (optionalUser.isEmpty()) {
            // 새로운 User 생성 및 저장
            user = User.create(authRequest.socialId(), Provider.KAKAO, authRequest.email(), authRequest.nickname(), Role.MEMBER);
            user = userRepository.save(user); // 저장 후 영속성 컨텍스트에 포함된 객체 반환
        } else {
            // 기존 User 사용
            user = optionalUser.get();
        }

        // 항상 userId가 있는 상태로 토큰 생성
        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(jwtToken);
    }
}
