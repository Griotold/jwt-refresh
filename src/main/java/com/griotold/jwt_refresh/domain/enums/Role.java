package com.griotold.jwt_refresh.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    MEMBER("회원"),
    ADMIN("관리자"),
    ;
    private final String description;

}
