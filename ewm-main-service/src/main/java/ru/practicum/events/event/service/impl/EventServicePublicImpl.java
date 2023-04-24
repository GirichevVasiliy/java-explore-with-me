package ru.practicum.events.event.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.service.EventServicePublic;
import ru.practicum.events.event.dto.sort.SortEvent;
import ru.practicum.events.event.storage.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EventServicePublicImpl implements EventServicePublic {
    private final EventRepository eventRepository;

    @Autowired
    public EventServicePublicImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventShortDto> getAllPublicEvents(String text, List<Long> categories, boolean paid, LocalDateTime rangeStart,
                                                  LocalDateTime rangeEnd, boolean onlyAvailable, String sort, int from, int size) {


        return null;
    }

    @Override
    public EventShortDto getPublicEventById(Long id) {
        return null;
    }
}
