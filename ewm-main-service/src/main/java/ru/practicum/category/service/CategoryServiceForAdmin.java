package ru.practicum.category.service;

import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;

public interface CategoryServiceForAdmin {
    CategoryDto addCategory(NewCategoryDto newCategoryDto);
    void deleteCategoryById(Long catId);
    CategoryDto updateCategory(Long catId, NewCategoryDto newCategoryDto);
}
