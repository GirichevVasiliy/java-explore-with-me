package ru.practicum.events.event.service;

import ru.practicum.events.event.dto.*;

import java.util.List;

public interface EventServicePrivate {
    List<EventShortDto> getAllPrivateEventsByUserId(Long userId, int from, int size);
    EventFullDto addPrivateEventByUserId(Long userId, NewEventDto newEventDto);
    EventFullDto getPrivateEventByIdAndByUserId(Long userId, Long eventId);
    EventFullDto updatePrivateEventByIdAndByUserId(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest);
    List<ParticipationRequestDto> getAllPrivateEventsByRequests(Long userId, Long eventId);
}
