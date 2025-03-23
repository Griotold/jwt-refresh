package com.griotold.jwt_refresh.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Provider {

    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버"),
    ;
    private final String description;
}
