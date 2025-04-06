package org.infy.service;

import org.infy.dto.ExpenseDTO;
import org.infy.entity.Expense;
import org.infy.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setCategory(expenseDTO.getCategory());
        Expense savedExpense = expenseRepository.save(expense);
        return convertToDTO(savedExpense);
    }

    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ExpenseDTO> updateExpense(Long id, ExpenseDTO expenseDTO) {
        return expenseRepository.findById(id).map(expense -> {
            expense.setDescription(expenseDTO.getDescription());
            expense.setAmount(expenseDTO.getAmount());
            expense.setDate(expenseDTO.getDate());
            expense.setCategory(expenseDTO.getCategory());
            Expense updatedExpense = expenseRepository.save(expense);
            return convertToDTO(updatedExpense);
        });
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }



    private ExpenseDTO convertToDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setCategory(expense.getCategory());
        return dto;
    }
}
