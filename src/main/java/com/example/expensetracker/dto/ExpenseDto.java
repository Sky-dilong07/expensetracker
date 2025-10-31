package com.example.expensetracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpenseDto {
    private Long id;
    private String title;
    private Double amount;
    private LocalDate date;
    private String note;
    private String paymentMethod;
    private String categoryName; // just store category name for simplicity
    private LocalDateTime createdAt;

    public ExpenseDto() {}

    public ExpenseDto(Long id, String title, Double amount, LocalDate date,
                      String note, String paymentMethod, String categoryName, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.paymentMethod = paymentMethod;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
