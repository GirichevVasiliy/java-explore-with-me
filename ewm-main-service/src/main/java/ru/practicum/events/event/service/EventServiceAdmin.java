package ru.practicum.events.event.service;

import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.UpdateEventAdminRequest;


import java.time.LocalDateTime;
import java.util.List;

public interface EventServiceAdmin {
    List<EventFullDto> getAllEventsForAdmin(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, int from, int size);
    EventFullDto updateEventById(Long eventId, UpdateEventAdminRequest updateEventAdminRequest);
}
