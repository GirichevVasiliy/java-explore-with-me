package ru.practicum.events.request.dto;

import ru.practicum.events.event.dto.ParticipationRequestDto;

public class EventRequestStatusUpdateResult { // Результат подтверждения/отклонения заявок на участие в событии
    private ParticipationRequestDto confirmedRequests; //
    private ParticipationRequestDto rejectedRequests;
}
