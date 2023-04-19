package ru.practicum.category.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class NewCategoryDto { // Данные для добавления новой категории
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // Название категории example: Концерты
}
