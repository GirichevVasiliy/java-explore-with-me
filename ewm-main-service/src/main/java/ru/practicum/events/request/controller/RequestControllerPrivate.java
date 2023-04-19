package ru.practicum.events.request.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.events.request.service.RequestServicePrivate;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@Slf4j
public class RequestControllerPrivate {
    private final RequestServicePrivate requestServicePrivate;
    @Autowired
    public RequestControllerPrivate(RequestServicePrivate requestServicePrivate) {
        this.requestServicePrivate = requestServicePrivate;
    }


}
