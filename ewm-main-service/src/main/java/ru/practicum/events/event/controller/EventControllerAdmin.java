package ru.practicum.events.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.dto.UpdateEventAdminRequest;
import ru.practicum.events.event.dto.stateDto.EventStateDto;
import ru.practicum.events.event.service.EventServiceAdmin;
import ru.practicum.util.util.DateFormatter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;
import java.time.LocalDateTime;
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
    List<EventFullDto> getAllEventsForAdmin(@RequestParam List<Long> users,
                                                 @RequestParam List<EventStateDto> states,
                                                 @RequestParam List<Long> categories,
                                                 @RequestParam String rangeStart,
                                                 @RequestParam String rangeEnd,
                                                 @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                 @Positive @Min(1) @RequestParam(defaultValue = "10") Integer size) {
        LocalDateTime newRangeStart = DateFormatter.formatDate(rangeStart);
        LocalDateTime newRangeEnd = DateFormatter.formatDate(rangeEnd);
        return eventServiceAdmin.getAllEventsForAdmin(users, states, categories, newRangeStart, newRangeEnd, from, size);
    }
    @PatchMapping("/{eventId}")
    EventFullDto updateEventById( @NotNull @PathVariable Long eventId,
                                  @RequestParam UpdateEventAdminRequest updateEventAdminRequest){
        return eventServiceAdmin.updateEventById(eventId, updateEventAdminRequest);
    }
}
