package ru.practicum.category.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.service.CategoryServicePublic;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@Slf4j
@Validated
public class CategoryControllerPublic {
    private final CategoryServicePublic categoryService;

    @Autowired
    public CategoryControllerPublic(CategoryServicePublic categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getAllCategory(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                            @Positive @RequestParam(defaultValue = "10") Integer size) {
        return categoryService.getAllCategory(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        return categoryService.getCategoryById(catId);
    }
}
