package com.griotold.jwt_refresh.presentation;

public record AuthRequest(
        String socialId,
        String email,
        String nickname
) {
}
