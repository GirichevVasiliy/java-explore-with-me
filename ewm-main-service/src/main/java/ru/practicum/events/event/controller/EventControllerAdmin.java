package ru.practicum.events.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.UpdateEventAdminRequest;
import ru.practicum.events.event.service.EventServiceAdmin;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@Slf4j
public class EventControllerAdmin {
    private final EventServiceAdmin eventServiceAdmin;

    @Autowired
    public EventControllerAdmin(EventServiceAdmin eventServiceAdmin) {
        this.eventServiceAdmin = eventServiceAdmin;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<EventFullDto> getAllEventsForAdmin(@RequestParam(required = false) List<Long> users,
                                            @RequestParam(required = false) List<String> states,
                                            @RequestParam(required = false) List<Long> categories,
                                            @RequestParam(required = false) String rangeStart,
                                            @RequestParam(required = false) String rangeEnd,
                                            @PositiveOrZero @RequestParam(defaultValue = "0", required = false) Integer from,
                                            @Positive @Min(1) @RequestParam(defaultValue = "10", required = false) Integer size) {

        return eventServiceAdmin.getAllEventsForAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    EventFullDto updateEventById(@PathVariable Long eventId,
                                 @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return eventServiceAdmin.updateEventById(eventId, updateEventAdminRequest);
    }
}
