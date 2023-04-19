package ru.practicum.category.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.service.CategoryServiceForAdmin;
import ru.practicum.category.storage.CategoryRepository;

@Service
@Slf4j
@Transactional
public class CategoryServiceForAdminImpl implements CategoryServiceForAdmin {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceForAdminImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long catId) {

    }

    @Override
    public CategoryDto updateCategory(Long catId, NewCategoryDto newCategoryDto) {
        return null;
    }
}
