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
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ConflictDeleteException;
import ru.practicum.exception.ConflictNameCategoryException;
import ru.practicum.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceAdminImpl implements CategoryServiceAdmin {
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @Autowired
    public CategoryServiceAdminImpl(CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        log.info("Запрос на добавление категории {}", newCategoryDto.getName());
        Category category = CategoryMapper.newCategoryDtoToCategory(newCategoryDto);
        return getCategoryDto(category, newCategoryDto.getName());
    }

    @Override
    public void deleteCategoryById(Long catId) {
        log.info("Запрос на удаление категории c id= {}", catId);
        Category category = getCategoryById(catId);
        if (isRelatedEvent(category)) {
            throw new ConflictDeleteException("Существуют события, связанные с категорией " + category.getName());
        }
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updateCategory(Long catId, CategoryDto categoryDto) {
        log.info("Запрос на обновлении категории c id= {}", catId);
        Category category = getCategoryById(catId);
        category.setId(catId);
        category.setName(categoryDto.getName());
        return getCategoryDto(category, category.getName());
    }

    private CategoryDto getCategoryDto(Category category, String name) {
        try {
            return CategoryMapper.categoryToCategoryDto(categoryRepository.save(category));
        } catch (DataIntegrityViolationException e) {
            log.warn("Нарушена уникальность имени категории {} уже используется", name);
            throw new ConflictNameCategoryException("Имя категории должно быть уникальным, "
                    + name + " уже используется");
        } catch (Exception e) {
            log.warn("Запрос на добавлении категории {} составлен не корректно", name);
            throw new BadRequestException("Запрос на добавлении категории " + name + " составлен не корректно ");
        }
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Категория c id = " + id + " не найдена"));
    }

    private boolean isRelatedEvent(Category category) {
        List<Event> findEventByCategory = eventRepository.findEventByCategoryIs(category);
        return !findEventByCategory.isEmpty();
    }
}