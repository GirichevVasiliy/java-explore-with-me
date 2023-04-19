package ru.practicum.events.event.service;

import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.UpdateEventAdminRequest;
import ru.practicum.events.event.dto.stateDto.EventStateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventServiceForAdmin {
    List<EventFullDto> getAllEventsForAdmin(List<Long> users, List<EventStateDto> states, List<Long> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size);
    EventFullDto updateEventById(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);
}
