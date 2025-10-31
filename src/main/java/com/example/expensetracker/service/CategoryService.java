package com.example.expensetracker.service;

import com.example.expensetracker.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    void deleteCategory(Long id);
}
