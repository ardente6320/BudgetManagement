package com.oliver.toy.budgetmanagementapi.services;

import java.util.Optional;

import javax.security.auth.login.AccountException;

import com.oliver.toy.budgetmanagementapi.models.User;
import com.oliver.toy.budgetmanagementapi.repositorys.AccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;  

    public User findUserByUserIdAndPassword(String userId, String password) throws AccountException{
        Optional<User> user = accountRepository.findUserByUserIdAndPassword(userId,password);

        return user.orElseThrow(() -> new AccountException());
    }

    @Transactional(rollbackFor = AccountException.class)
    public User save(String userId, String password) throws AccountException{
        Optional<User> user = accountRepository.findUserByUserIdAndPassword(userId,password);

        return accountRepository.save(user.orElse(new User(userId,password)));
    }
}
