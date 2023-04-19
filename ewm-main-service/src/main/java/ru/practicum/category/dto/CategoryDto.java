package ru.practicum.category.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto { // Категория
   private Long id; // Идентификатор категории
    @NotBlank(message = "Поле name не должно быть пустым")
    String name; // example: Концерты Название категории

}
