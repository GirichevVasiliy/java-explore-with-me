package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewCategoryDto { // Данные для добавления новой категории
    String name; // Название категории example: Концерты
}
