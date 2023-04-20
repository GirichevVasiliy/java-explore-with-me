package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@NoArgsConstructor
@Builder
public class NewCategoryDto { // Данные для добавления новой категории
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // Название категории example: Концерты

    public NewCategoryDto(String name) {
        this.name = name;
    }
}
