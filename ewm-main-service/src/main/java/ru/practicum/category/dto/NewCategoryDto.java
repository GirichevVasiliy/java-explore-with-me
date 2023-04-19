package ru.practicum.category.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCategoryDto { // Данные для добавления новой категории
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // Название категории example: Концерты
}
