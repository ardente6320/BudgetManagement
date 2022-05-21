package com.oliver.toy.budgetmanagementapi.services;

import java.util.Optional;

import com.oliver.toy.budgetmanagementapi.constants.ApiCode;
import com.oliver.toy.budgetmanagementapi.exceptions.ApiException;
import com.oliver.toy.budgetmanagementapi.models.User;
import com.oliver.toy.budgetmanagementapi.repositorys.AccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * 계정 서비스
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;  

    public User findUserByUserIdAndPassword(String userId, String password) throws ApiException{
        Optional<User> user = accountRepository.findUserByUserIdAndPassword(userId,password);

        return user.orElseThrow(() -> new ApiException(ApiCode.INVALID_USER));
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(String userId, String password) throws Exception{
        Optional<User> user = accountRepository.findUserByUserIdAndPassword(userId,password);

        return accountRepository.save(user.orElse(new User(userId,password)));
    }
}
