package com.oliver.toy.budgetmanagementapi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    NORMAL("U"),
    STOP("T");

    private String status;
}
