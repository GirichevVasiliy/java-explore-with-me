package ru.practicum.events.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.service.EventServicePublic;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@Slf4j
public class EventControllerPublic {
    private final EventServicePublic eventServicePublic;

    @Autowired
    public EventControllerPublic(EventServicePublic eventServicePublic) {
        this.eventServicePublic = eventServicePublic;
    }

    /*
    EventShortDto getPublicEventById(Long id);*/
    @GetMapping()
    List<EventShortDto> getAllPublicEvents(@RequestParam(required = false) String text,
                                           @RequestParam(required = false) List<Long> categories,
                                           @RequestParam(required = false) Boolean paid,
                                           @RequestParam(required = false) LocalDateTime rangeStart,
                                           @RequestParam(required = false) LocalDateTime rangeEnd,
                                           @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                           @RequestParam(defaultValue = "EVENT_DATE") String sort,
                                           @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                           @Positive @Min(1) @RequestParam(defaultValue = "10") Integer size) {
        return eventServicePublic.getAllPublicEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{id}")
    EventShortDto getPublicEventById(@PathVariable Long id) {
        return eventServicePublic.getPublicEventById(id);
    }
}
