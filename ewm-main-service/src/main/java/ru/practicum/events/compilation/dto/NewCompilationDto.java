package ru.practicum.events.compilation.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.events.event.dto.EventShortDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Builder
public class NewCompilationDto { // Подборка событий
    List<EventShortDto> events;
    @NotNull(message = "pinned не может быть пустым")
    boolean pinned; // Закреплена ли подборка на главной странице сайта example: true
    @NotNull(message = "title не может быть пустым")
    String title; // Заголовок подборки example: Летние концерты
}
