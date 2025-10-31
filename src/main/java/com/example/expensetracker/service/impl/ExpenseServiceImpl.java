package com.example.expensetracker.service.impl;

import com.example.expensetracker.dto.ExpenseDto;
import com.example.expensetracker.model.Category;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ExpenseDto addExpense(ExpenseDto dto) {
        // 1️. Try to find existing category
        Category category = categoryRepository.findByName(dto.getCategoryName());

        // 2️. If not found, auto-create it
        if (category == null) {
            category = new Category();
            category.setName(dto.getCategoryName());
            category.setType("Expense"); // default type
            category.setDescription("Auto-created category");
            category.setCreatedAt(LocalDateTime.now());
            category = categoryRepository.save(category);
        }

        // 3. Convert DTO → Entity
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setNote(dto.getNote());
        expense.setPaymentMethod(dto.getPaymentMethod());
        expense.setCategory(category);
        expense.setCreatedAt(LocalDateTime.now());

        // 4️. Save and return as DTO
        Expense savedExpense = expenseRepository.save(expense);
        return mapToDto(savedExpense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found with id: " + id));
        return mapToDto(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public double getTotalExpense() {
        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    //  Helper: Entity → DTO
    private ExpenseDto mapToDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setNote(expense.getNote());
        dto.setPaymentMethod(expense.getPaymentMethod());
        dto.setCategoryName(expense.getCategory().getName());
        dto.setCreatedAt(expense.getCreatedAt());
        return dto;
    }
}
