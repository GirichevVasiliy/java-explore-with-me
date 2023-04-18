package ru.practicum.events.compilation.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.events.event.dto.EventShortDto;

import java.util.List;

@Value
@Builder
public class CompilationDto { //Подборка событий
    List<EventShortDto> events;
    Long id;
    boolean pinned; // Закреплена ли подборка на главной странице сайта example: true
    String title; // Заголовок подборки example: Летние концерты
}
