package com.oliver.toy.budgetmanagementapi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiCode {
    
    SUCCESS("000","Success"),
    FAILED ("999","Unexpected Exception has caughted");

    private String code;
    private String message;
}
