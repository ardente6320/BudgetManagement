package com.oliver.toy.budgetmanagementapi.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.oliver.toy.budgetmanagementapi.constants.ApiCode;

import lombok.Getter;
import lombok.ToString;

/**
 * Response Body Template
 */
@Getter
@ToString
@JsonInclude(Include.NON_NULL)
public class ResponseTemplate {
    /** 응답 코드 */
    private final String code;
    /** 응답 메시지 */
    private final String message;
    /** 응답 데이터 */
    private final Object body;

    public ResponseTemplate(ApiCode apiCode) {
        this.code     = apiCode.getCode();
        this.message  = apiCode.getMessage();
        this.body     = null;
    }

    public ResponseTemplate(ApiCode apiCode,Object responseData) {
        this.code     = apiCode.getCode();
        this.message  = apiCode.getMessage();
        this.body     = responseData;
    }
}