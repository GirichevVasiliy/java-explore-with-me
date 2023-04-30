package ru.practicum.events.event.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import ru.practicum.explorewithme.stats.client.StatsClient;
import ru.practicum.explorewithme.stats.dto.HitDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EventServicePublicImpl implements EventServicePublic {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final EventRepository eventRepository;
    private final StatsClient client;
    @Value("${app.name}")
    private String appName;

    @Autowired
    public EventServicePublicImpl(EventRepository eventRepository, StatsClient client) {
        this.eventRepository = eventRepository;
        this.client = client;
    }

    @Override
    public List<EventShortDto> getAllPublicEvents(String text, List<Long> categories, Boolean paid, String rangeStart,
                                                  String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size, HttpServletRequest request) {
        log.info("Получен запрос на получение всех событий (публичный)");
        HitDto hitDto = HitDto.builder()
                .app(appName)
                .uri("/events")
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .build();
        client.hitRequest(hitDto);
        List<Event> events = eventRepository.findAllByPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        return events.stream().map(EventMapper::eventToeventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto getPublicEventById(Long id, HttpServletRequest request) {
        log.info("Получен запрос на получение события по id= " + id + " (публичный)");
        Event event = eventRepository.findEventByIdAndStateIs(id, EventState.PUBLISHED).orElseThrow(()
                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));
        event.setViews(event.getViews() + 1L);
        HitDto hitDto = createHitDtoToStats(request);
        client.hitRequest(hitDto);
        return EventMapper.eventToEventFullDto(event);
    }

    private HitDto createHitDtoToStats(HttpServletRequest request) {
        HitDto hitDto = HitDto.builder()
                .app(appName)
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .build();
        return hitDto;
    }
}
