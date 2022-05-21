package com.oliver.toy.budgetmanagementapi.repositorys;

import java.util.List;
import java.util.Optional;

import com.oliver.toy.budgetmanagementapi.models.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    public Optional<List<Budget>> findByUserNo(long userNo);

    public Optional<Budget> findByBudgetId(Long budgetId);
}
