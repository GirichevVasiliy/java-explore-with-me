package ru.practicum.category.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.service.CategoryServiceForAdmin;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/categories")
@Slf4j
public class CategoryControllerAdmin {
    private final CategoryServiceForAdmin service;

    @Autowired
    public CategoryControllerAdmin(CategoryServiceForAdmin service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(@Validated @RequestBody NewCategoryDto newCategoryDto) {
        return service.addCategory(newCategoryDto);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@NotNull @PathVariable Long catId,
                                      @Validated @RequestBody CategoryDto newCategoryDto) {
        return service.updateCategory(catId, newCategoryDto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@NotNull @PathVariable Long catId) {
        service.deleteCategoryById(catId);
    }
}
