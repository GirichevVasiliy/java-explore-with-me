package ru.practicum.events.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.dto.NewEventDto;
import ru.practicum.events.event.dto.UpdateEventUserRequest;
import ru.practicum.events.event.service.EventServicePrivate;
import ru.practicum.events.request.dto.EventRequestStatusUpdateRequest;
import ru.practicum.events.request.dto.EventRequestStatusUpdateResult;
import ru.practicum.events.request.dto.ParticipationRequestDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/events")
@Slf4j
public class EventControllerPrivate {
    private final EventServicePrivate eventServicePrivate;

    @Autowired
    public EventControllerPrivate(EventServicePrivate eventServicePrivate) {
        this.eventServicePrivate = eventServicePrivate;
    }

    @GetMapping()
    List<EventShortDto> getAllPrivateEventsByUser(@NotNull @PathVariable Long userId,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @Min(1) @RequestParam(defaultValue = "10") Integer size) {
        return eventServicePrivate.getAllPrivateEventsByUserId(userId, from, size);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    EventFullDto addPrivateEventByUserId(@NotNull @PathVariable Long userId,
                                         @Validated @RequestBody NewEventDto newEventDto) {
        return eventServicePrivate.addPrivateEventByUserId(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    EventFullDto getPrivateEventByIdAndByUserId(@NotNull @PathVariable Long userId,
                                                @NotNull @PathVariable Long eventId) {
        return eventServicePrivate.getPrivateEventByIdAndByUserId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    EventFullDto updatePrivateEventByIdAndByUserId(@NotNull @PathVariable Long userId,
                                                   @NotNull @PathVariable Long eventId,
                                                   @Validated @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        return eventServicePrivate.updatePrivateEventByIdAndByUserId(userId, eventId, updateEventUserRequest);
    }

    @GetMapping("/{eventId}/request")
    List<ParticipationRequestDto> getAllPrivateEventsByRequests(@NotNull @PathVariable Long userId,
                                                                @NotNull @PathVariable Long eventId) {
        return eventServicePrivate.getAllPrivateEventsByRequests(userId, eventId);
    }

    @PatchMapping("/{eventId}/request")
    EventRequestStatusUpdateResult updateEventRequestStatus(@NotNull @PathVariable Long userId,
                                                            @NotNull @PathVariable Long eventId,
                                                            @RequestBody EventRequestStatusUpdateRequest request) {
        return eventServicePrivate.updateEventRequestStatus(userId, eventId, request);
    }
}
