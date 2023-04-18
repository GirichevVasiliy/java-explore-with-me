package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryDto { // Категория
    Long id; // Идентификатор категории
    String name; // example: Концерты Название категории
}
