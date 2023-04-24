package ru.practicum.events.event.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.mapper.EventMapper;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.model.EventState;
import ru.practicum.events.event.service.EventServicePublic;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<EventShortDto> getAllPublicEvents(String text, List<Long> categories, boolean paid, String rangeStart,
                                                  String rangeEnd, boolean onlyAvailable, String sort, int from, int size) {
// статистика
        List<Event> events = eventRepository.findAllByPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        return events.stream().map(EventMapper::eventToeventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto getPublicEventById(Long id) {
        // статистика
        Event event =  eventRepository.findEventByIdAndStateIs(id, EventState.PUBLISHED).orElseThrow(()
                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));
       return EventMapper.eventToEventFullDto(event);
    }
}
