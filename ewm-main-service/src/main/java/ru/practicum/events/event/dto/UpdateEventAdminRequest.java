package ru.practicum.events.event.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.events.event.dto.stateDto.ActionStateDto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Value
@Builder
public class UpdateEventAdminRequest {
    @Size(min = 20, max = 2000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 2000")
    String annotation;
    Long category;
    @Size(min = 20, max = 7000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 7000")
    String description;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String eventDate;
    LocationDto location;
    Boolean paid;
    @PositiveOrZero
    Integer participantLimit;
    Boolean requestModeration;
    ActionStateDto stateAction;
    @Size(min = 5, max = 255, message = "Минимальное кол-во символов для описания: 5. Максимальное: 255")
    String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
}
