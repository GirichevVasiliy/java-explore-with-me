package ru.practicum.events.event.service;

import ru.practicum.events.event.dto.*;
import ru.practicum.events.request.dto.ParticipationRequestDto;
import ru.practicum.events.request.dto.EventRequestStatusUpdateResult;
import ru.practicum.events.request.dto.EventRequestStatusUpdateRequest;

import java.util.List;

public interface EventServicePrivate {
    List<EventShortDto> getAllPrivateEventsByUserId(Long userId, Integer from, Integer size);
    EventFullDto addPrivateEventByUserId(Long userId, NewEventDto newEventDto);
    EventFullDto getPrivateEventByIdAndByUserId(Long userId, Long eventId);
    EventFullDto updatePrivateEventByIdAndByUserId(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest);
    List<ParticipationRequestDto> getAllPrivateEventsByRequests(Long userId, Long eventId);
    EventRequestStatusUpdateResult updateEventRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest request);
}
