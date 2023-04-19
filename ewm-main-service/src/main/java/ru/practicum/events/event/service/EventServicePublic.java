package ru.practicum.events.event.service;

import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.service.sort.SortEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface EventServicePublic {
    List<EventShortDto> getAllPublicEvents(String text, List<Long> categories, boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, boolean onlyAvailable, SortEvent sort, int from, int size);
    EventShortDto getPublicEventById(Long id);
}
