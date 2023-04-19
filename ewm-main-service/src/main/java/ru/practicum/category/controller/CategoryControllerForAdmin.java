package ru.practicum.category.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.service.CategoryServiceForAdmin;

@RestController
@RequestMapping(path = "/admin/categories")
@Slf4j
public class CategoryControllerForAdmin {
    private final CategoryServiceForAdmin service;

    @Autowired
    public CategoryControllerForAdmin(CategoryServiceForAdmin service) {
        this.service = service;
    }

    @PostMapping
    public CategoryDto addCategory(@Validated @RequestBody NewCategoryDto newCategoryDto) {
        return service.addCategory(newCategoryDto);
    }
    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@PathVariable Long catId,
                                     @Validated @RequestBody NewCategoryDto newCategoryDto) {
        return service.updateCategory(catId, newCategoryDto);
    }
    @DeleteMapping("/{catId}")
    public void deleteCategoryById(@PathVariable Long catId) {
        service.deleteCategoryById(catId);
    }
}
