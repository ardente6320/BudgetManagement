package com.oliver.toy.budgetmanagementapi.controllers;

import java.util.Map;
import java.util.Optional;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

import com.oliver.toy.budgetmanagementapi.models.User;
import com.oliver.toy.budgetmanagementapi.services.AccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping(value = "/auth", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> login(HttpServletRequest req, @RequestBody Map<String,Object> body) throws AccountException{
        String userId = body.get("id").toString();
        String password = body.get("password").toString();
        User user = accountService.findUserByUserIdAndPassword(userId,password);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> registAccount(HttpServletRequest req, @RequestBody Map<String,Object> body) throws AccountException{
        String userId = body.get("id").toString();
        String password = body.get("password").toString();
        User user = accountService.save(userId,password);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
