package com.oliver.toy.budgetmanagementapi.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oliver.toy.budgetmanagementapi.constants.ApiCode;
import com.oliver.toy.budgetmanagementapi.exceptions.ApiException;
import com.oliver.toy.budgetmanagementapi.models.User;
import com.oliver.toy.budgetmanagementapi.services.AccountService;
import com.oliver.toy.budgetmanagementapi.utils.ResponseTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * 계정 API
 */
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    /**
     * 로그인 승인
     * @param req
     * @param body
     * @return ResponseEntity
     * @throws ApiException
     */
    @PostMapping(value = "/auth", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> login(HttpServletRequest req, @RequestBody Map<String,Object> body) throws ApiException{
        String userId = body.get("id").toString();
        String password = body.get("password").toString();
        User user = accountService.findUserByUserIdAndPassword(userId,password);

        return new ResponseEntity<>(new ResponseTemplate(ApiCode.SUCCESS,user), HttpStatus.OK);
    }
    
    /**
     * 회원가입
     * @param req
     * @param body
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> registAccount(HttpServletRequest req, @RequestBody Map<String,Object> body) throws Exception{
        String userId = body.get("id").toString();
        String password = body.get("password").toString();
        User user = accountService.save(userId,password);

        return new ResponseEntity<>(new ResponseTemplate(ApiCode.SUCCESS,user), HttpStatus.OK);
    }
}
