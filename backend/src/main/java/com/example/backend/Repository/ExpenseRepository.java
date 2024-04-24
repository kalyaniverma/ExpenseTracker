package com.example.backend.Repository;

import com.example.backend.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByUserId(int userId);
    List<Expense> findAllByOrderByDateDesc(int userId);
    List<Expense> findAllByOrderByAmountDesc(int userId);
    List<Expense> findAllByOrderByAmountAsc(int userId);
    List<Expense> findByUserIdAndCategory(int userId, String category);
}
