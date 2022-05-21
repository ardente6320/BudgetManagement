package com.oliver.toy.budgetmanagementapi.exceptions;

import com.oliver.toy.budgetmanagementapi.constants.ApiCode;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private ApiCode apiCode;

    public ApiException(ApiCode apiCode){
        super(apiCode.getMessage());
        this.apiCode = apiCode;
    }
}
