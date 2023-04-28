package ru.practicum.events.event.service;

import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventServicePublic {
    List<EventShortDto> getAllPublicEvents(String text, List<Long> categories, boolean paid, String rangeStart, String rangeEnd, boolean onlyAvailable, String sort, int from, int size, HttpServletRequest request);

    EventFullDto getPublicEventById(Long id, HttpServletRequest request);
}
