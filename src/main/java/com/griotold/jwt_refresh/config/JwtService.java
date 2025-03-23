package com.griotold.jwt_refresh.config;

import com.griotold.jwt_refresh.domain.entity.User;
import com.griotold.jwt_refresh.domain.enums.Provider;
import com.griotold.jwt_refresh.domain.enums.Role;
import com.griotold.jwt_refresh.infra.LoginUser;
import com.griotold.jwt_refresh.presentation.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractSocialId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(User user) {
        return buildToken(user, jwtExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return buildToken(extraClaims, userDetails, jwtExpiration);
        return null;
    }

    public String generateRefreshToken(User user) {
        return buildToken(user, refreshExpiration);
    }

//    public String createAccessToken(Long userId, Role role, String username) {
//        return Jwts.builder()
//                .claim("user_id", userId)
//                .claim("role", role.name())
//                .subject(username) // sub 클레임 추가
//                .issuer(issuer)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
//                .signWith(secretKey, io.jsonwebtoken.SignatureAlgorithm.HS512)
//                .compact();
//    }

    private String buildToken(User user, long expiration) {
        return Jwts.builder()
                .claim("user_id", user.getId())
                .claim("role", user.getRole().name())
                        .setSubject(user.getSocialId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String token, User user) {
        final String socialId = extractSocialId(token);
        return (socialId.equals(user.getSocialId())) && !isTokenExpired(token);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String socialId = extractSocialId(token);
        return (socialId.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
