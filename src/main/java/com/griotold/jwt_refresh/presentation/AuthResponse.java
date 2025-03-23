package com.griotold.jwt_refresh.presentation;

public record AuthResponse(String jwtToken, String refreshToken) {
}
