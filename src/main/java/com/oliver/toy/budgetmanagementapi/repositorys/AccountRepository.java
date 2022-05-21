package com.oliver.toy.budgetmanagementapi.repositorys;

import java.util.Optional;

import com.oliver.toy.budgetmanagementapi.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<User,Long> {
    
    Optional<User> findUserByUserIdAndPassword(String userId, String password);
}
