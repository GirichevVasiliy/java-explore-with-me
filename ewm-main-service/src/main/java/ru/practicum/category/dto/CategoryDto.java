package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class CategoryDto { // Категория
    Long id; // Идентификатор категории
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // example: Концерты Название категории
}
