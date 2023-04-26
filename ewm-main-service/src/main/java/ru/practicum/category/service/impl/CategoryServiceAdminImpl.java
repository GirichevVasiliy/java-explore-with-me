package ru.practicum.category.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.service.CategoryServiceAdmin;
import ru.practicum.category.storage.CategoryRepository;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ConflictDeleteException;
import ru.practicum.exception.ConflictNameCategoryException;
import ru.practicum.util.FindObjectInRepository;

@Service
@Slf4j
public class CategoryServiceAdminImpl implements CategoryServiceAdmin {
    private final CategoryRepository categoryRepository;
    private final FindObjectInRepository findObjectInRepository;

    @Autowired
    public CategoryServiceAdminImpl(CategoryRepository categoryRepository, FindObjectInRepository findObjectInRepository) {
        this.categoryRepository = categoryRepository;
        this.findObjectInRepository = findObjectInRepository;
    }

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        Category category = CategoryMapper.newCategoryDtoToCategory(newCategoryDto);
        return getCategoryDto(category, newCategoryDto.getName());
    }

    @Override
    public void deleteCategoryById(Long catId) {
        Category category = findObjectInRepository.getCategoryById(catId);
        if (findObjectInRepository.isRelatedEvent(category)) {
            throw new ConflictDeleteException("Существуют события, связанные с категорией " + category.getName());
        }
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updateCategory(Long catId, CategoryDto categoryDto) {
        Category category = findObjectInRepository.getCategoryById(catId);
        category.setId(catId);
        if (categoryDto.getName() != null) {
            category.setName(categoryDto.getName());
        }
        return getCategoryDto(category, category.getName());
    }

    private CategoryDto getCategoryDto(Category category, String name) {
        try {
            return CategoryMapper.categoryToCategoryDto(categoryRepository.save(category));
        } catch (DataIntegrityViolationException e) {
            throw new ConflictNameCategoryException("Имя категории должно быть уникальным, "
                    + name + " уже используется");
        } catch (Exception e) {
            throw new BadRequestException("Запрос на добавлении категории " + name + " составлен не корректно ");
        }
    }
}