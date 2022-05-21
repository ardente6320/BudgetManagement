package com.oliver.toy.budgetmanagementapi.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import com.oliver.toy.budgetmanagementapi.models.Budget;
import com.oliver.toy.budgetmanagementapi.services.BudgetService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;
    
    @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Budget>> getBudgetList(HttpServletRequest req){
        long userNo = 1;
        Optional<List<Budget>> budgetList = budgetService.findByUserNo(userNo);

        return new ResponseEntity<List<Budget>>(budgetList.get(),HttpStatus.OK);
    }

    @GetMapping(value = "/{budgetId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Budget> getBudget(HttpServletRequest req, @PathVariable("budgetId") Long budgetId){
        Optional<Budget> budget = budgetService.findByBudgetId(budgetId);

        return new ResponseEntity<Budget>(budget.get(),HttpStatus.OK);
    }
}
