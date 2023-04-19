package ru.practicum.events.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@Slf4j
public class EventControllerPrivate {
}
