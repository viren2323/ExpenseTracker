package org.infy.controller;

import org.infy.dto.ExpenseDTO;
import org.infy.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin()
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.addExpense(expenseDTO));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        return expenseService.updateExpense(id, expenseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

}
