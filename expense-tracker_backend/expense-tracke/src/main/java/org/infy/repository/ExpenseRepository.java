package org.infy.repository;

import org.infy.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Expense> findByCategory(String category);
}
