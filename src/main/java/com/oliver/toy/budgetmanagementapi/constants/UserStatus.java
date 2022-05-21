package com.oliver.toy.budgetmanagementapi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 상태
 */
@Getter
@AllArgsConstructor
public enum UserStatus {
    NORMAL("U"),
    STOP("T");

    private String status;
}
