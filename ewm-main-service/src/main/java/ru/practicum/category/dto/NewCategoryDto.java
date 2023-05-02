package ru.practicum.category.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCategoryDto { // Данные для добавления новой категории
    @Size(max = 255, message = "Максимальное кол-во символов для описания: 255")
    @NotBlank(message = "Поле name не должно быть пустым")
    private String name; // Название категории example: Концерты
}
