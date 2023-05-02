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
import ru.practicum.explorewithme.stats.dto.StatsDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
        if (events.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> uris = events.stream().map(e -> request.getRequestURI() + "/" + e.getId()).collect(Collectors.toList());
        LocalDateTime start = LocalDateTime.of(LocalDate.of(1900, 1, 1), LocalTime.of(1, 1, 1));
        LocalDateTime end = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        List<StatsDto> stats = client.getStats(start, end, uris, false);
        fillEventViews(events, stats, request.getRequestURI());
        return events.stream().map(EventMapper::eventToeventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto getPublicEventById(Long id, HttpServletRequest request) {
        log.info("Получен запрос на получение события по id= {} (публичный)", id);
        HitDto hitDto = createHitDtoToStats(request);
        client.hitRequest(hitDto);

        Event event = eventRepository.findEventByIdAndStateIs(id, EventState.PUBLISHED).orElseThrow(()
                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));
        LocalDateTime start = LocalDateTime.of(LocalDate.of(1900, 1, 1), LocalTime.of(0, 0, 1));

        List<StatsDto> stats = client.getStats(start, LocalDateTime.now(), List.of(request.getRequestURI()), false);

        Long hits = stats.stream().map(StatsDto::getHits).reduce(0L, Long::sum);

        event.setViews(hits);
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

    private void fillEventViews(List<Event> events, List<StatsDto> stats, String baseUri) {
        if (!stats.isEmpty()) {
            Map<String, Long> statsByUri = stats.stream()
                    .collect(Collectors.groupingBy(StatsDto::getUri, Collectors.summingLong(v -> v.getHits().longValue())));
            events.forEach(e -> {
                Long views = statsByUri.get(baseUri + "/" + e.getId());
                if (views != null) {
                    e.setViews(views);
                }
            });
        }
    }
}
