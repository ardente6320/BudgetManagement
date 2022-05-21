package com.oliver.toy.budgetmanagementapi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API 응답 코드
 */
@AllArgsConstructor
@Getter
public enum ApiCode {
    
    //공통 응답 코드
    SUCCESS("000","Success"),
    ANNOMALY_ACCESS("E01","Annomaly Access"),
    INVALID_PARAM("E02","Invalid Param Check params key"),
    FAILED ("999","Unexpected Exception has caughted"),

    //계정 API 응답 코드
    INVALID_USER("A01","Invalid User. Try Again");

    private String code;
    private String message;
}
