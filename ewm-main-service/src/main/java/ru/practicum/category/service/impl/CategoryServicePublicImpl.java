package ru.practicum.category.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.service.CategoryServicePublic;
import ru.practicum.category.storage.CategoryRepository;
import ru.practicum.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CategoryServicePublicImpl implements CategoryServicePublic {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServicePublicImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategory(int from, int size) {
        log.info("Запрос на поиск всех категорий");
        return categoryRepository.findAll(PageRequest.of(from, size)).stream()
                .map(CategoryMapper::categoryToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        log.info("Запрос на поиск категории по id= {}", catId);
        Category category = categoryRepository.findById(catId).orElseThrow(()
                -> new ResourceNotFoundException("Категория c id = " + catId + " не найдена"));
        return CategoryMapper.categoryToCategoryDto(category);
    }
}
