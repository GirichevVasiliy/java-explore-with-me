package ru.practicum.category.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.service.CategoryServiceAdmin;
import ru.practicum.category.storage.CategoryRepository;
import ru.practicum.exception.ConflictDeleteException;
import ru.practicum.exception.ConflictNameCategoryException;
import ru.practicum.util.FindObjectInRepository;

@Service
@Slf4j
@Transactional
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
        try {
            return CategoryMapper.categoryToCategoryDto(categoryRepository.save(category));
        } catch (Exception e) {
            throw new ConflictNameCategoryException("Имя категории должно быть уникальным, "
                    + newCategoryDto.getName() + " уже используется");
        }
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
        if (findObjectInRepository.isSearchForUniqueCategory(categoryDto.getName())) {
            throw new ConflictNameCategoryException("Имя категории должно быть уникальным, "
                    + categoryDto.getName() + " уже используется");
        }
        Category category = findObjectInRepository.getCategoryById(catId);
        category.setId(catId);
        category.setName(categoryDto.getName());
        return CategoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }
}
