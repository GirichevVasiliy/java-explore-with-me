package ru.practicum.events.compilation.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Value
@Builder
public class NewCompilationDto { // Подборка событий
    List<Long> events;
    boolean pinned; // Закреплена ли подборка на главной странице сайта example: true
    @Size(max = 500, message = "Максимальное кол-во символов для описания: 500")
    @NotBlank(message = "title не может быть пустым")
    String title; // Заголовок подборки example: Летние концерты
}
