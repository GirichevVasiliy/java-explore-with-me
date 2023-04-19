package ru.practicum.events.event.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.dto.NewEventDto;
import ru.practicum.events.event.dto.UpdateEventUserRequest;
import ru.practicum.events.event.service.EventServicePrivate;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.events.request.dto.EventRequestStatusUpdateRequest;
import ru.practicum.events.request.dto.EventRequestStatusUpdateResult;
import ru.practicum.events.request.dto.ParticipationRequestDto;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EventServicePrivateImpl implements EventServicePrivate {
    private final EventRepository eventRepository;

    @Autowired
    public EventServicePrivateImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventShortDto> getAllPrivateEventsByUserId(Long userId, int from, int size) {
        return null;
    }
@Transactional
    @Override
    public EventFullDto addPrivateEventByUserId(Long userId, NewEventDto newEventDto) {
        return null;
    }

    @Override
    public EventFullDto getPrivateEventByIdAndByUserId(Long userId, Long eventId) {
        return null;
    }
    @Transactional
    @Override
    public EventFullDto updatePrivateEventByIdAndByUserId(Long userId, Long eventId, UpdateEventUserRequest updateEventUserRequest) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getAllPrivateEventsByRequests(Long userId, Long eventId) {
        return null;
    }
    @Transactional
    @Override
    public EventRequestStatusUpdateResult updateEventRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest request) {
        return null;
    }
}
