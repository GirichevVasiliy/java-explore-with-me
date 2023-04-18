package ru.practicum.events.request.dto;

import ru.practicum.events.request.model.RequestStatus;

import java.util.List;

public class EventRequestStatusUpdateRequest { // Изменение статуса запроса на участие в событии текущего пользователя
    List<Long> requestIds; // Идентификаторы запросов на участие в событии текущего пользователя
    RequestStatus status;
}
