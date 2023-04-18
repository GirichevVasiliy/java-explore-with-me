package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class NewCategoryDto { // Данные для добавления новой категории
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // Название категории example: Концерты
}
