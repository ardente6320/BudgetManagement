package com.oliver.toy.budgetmanagementapi.services;

import java.util.List;
import java.util.Optional;

import com.oliver.toy.budgetmanagementapi.models.Budget;
import com.oliver.toy.budgetmanagementapi.repositorys.BudgetRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 예산 서비스
 */
@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;

    public Optional<List<Budget>> findByUserNo(long userNo){
        return budgetRepository.findByUserNo(userNo);

    }

    public Optional<Budget> findByBudgetId(Long budgetId){
        return budgetRepository.findByBudgetId(budgetId);
    }
}
