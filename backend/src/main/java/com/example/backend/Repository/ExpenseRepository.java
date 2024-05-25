package com.example.backend.Repository;

import com.example.backend.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByUserId(int userId);
    List<Expense> findByUserIdOrderByDateDesc(int userId);
    List<Expense> findByUserIdOrderByAmountDesc(int userId);
    List<Expense> findByUserIdOrderByAmountAsc(int userId);
    List<Expense> findByUserIdAndCategory(int userId, String category);
    List<Expense> findByUserIdAndDate(int userId, LocalDate date);

    Optional<Expense> findExpenseByUserIdIsNull();
}
