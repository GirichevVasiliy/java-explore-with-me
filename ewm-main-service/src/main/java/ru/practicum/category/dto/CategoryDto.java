package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Value
@Builder
public class CategoryDto { // Категория
    Long id; // Идентификатор категории
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // example: Концерты Название категории

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
