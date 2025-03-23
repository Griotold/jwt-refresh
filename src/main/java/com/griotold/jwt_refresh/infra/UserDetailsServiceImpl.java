package com.griotold.jwt_refresh.infra;

import com.griotold.jwt_refresh.domain.entity.User;
import com.griotold.jwt_refresh.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userPS = userRepository.findByEmail(username)
                .orElseThrow(
                        () -> new InternalAuthenticationServiceException("인증 실패"));
        return new LoginUser(userPS);
    }
}
