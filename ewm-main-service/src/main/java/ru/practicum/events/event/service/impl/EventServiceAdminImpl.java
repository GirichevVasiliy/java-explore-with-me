package ru.practicum.events.event.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.UpdateEventAdminRequest;
import ru.practicum.events.event.dto.stateDto.EventStateDto;
import ru.practicum.events.event.service.EventServiceAdmin;
import ru.practicum.events.event.storage.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EventServiceAdminImpl implements EventServiceAdmin {
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceAdminImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventFullDto> getAllEventsForAdmin(List<Long> users, List<EventStateDto> states, List<Long> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size) {
        return null;
    }

    @Override
    public EventFullDto updateEventById(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
    }
}
