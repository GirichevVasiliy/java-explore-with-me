package ru.practicum.events.request.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Pattern;
@Value
@Builder
public class ParticipationRequestDto { // Заявка на участие в событии
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}:\\d{3}", message = "Invalid date format")
    String created; // 2022-09-06T21:10:05.432 Дата и время создания заявки
    Long event; // Идентификатор события
    Long id; //Идентификатор заявки
    Long requester; // Идентификатор пользователя, отправившего заявку
    String status; // example: PENDING Статус заявки

}
