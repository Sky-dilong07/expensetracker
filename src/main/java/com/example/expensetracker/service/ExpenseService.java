package com.example.expensetracker.service;

import com.example.expensetracker.dto.ExpenseDto;
import java.util.List;

public interface ExpenseService {
    ExpenseDto addExpense(ExpenseDto expenseDto);
    List<ExpenseDto> getAllExpenses();
    ExpenseDto getExpenseById(Long id);
    void deleteExpense(Long id);
    double getTotalExpense();
}
