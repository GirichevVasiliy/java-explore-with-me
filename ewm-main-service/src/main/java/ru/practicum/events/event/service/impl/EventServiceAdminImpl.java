package ru.practicum.events.event.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.practicum.category.model.Category;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.UpdateEventAdminRequest;
import ru.practicum.events.event.dto.stateDto.ActionStateDto;
import ru.practicum.events.event.mapper.EventMapper;
import ru.practicum.events.event.mapper.LocationMapper;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.model.EventState;
import ru.practicum.events.event.service.EventServiceAdmin;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ForbiddenEventException;
import ru.practicum.exception.ResourceNotFoundException;
import ru.practicum.util.FindObjectInRepository;
import ru.practicum.util.util.DateFormatter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceAdminImpl implements EventServiceAdmin {
    private final EventRepository eventRepository;
    private final FindObjectInRepository findObjectInRepository;

    @Autowired
    public EventServiceAdminImpl(EventRepository eventRepository,
                                 FindObjectInRepository findObjectInRepository) {
        this.eventRepository = eventRepository;
        this.findObjectInRepository = findObjectInRepository;
    }

    @Override
    public List<EventFullDto> getAllEventsForAdmin(List<Long> users, List<String> states, List<Long> categories,
                                                   String rangeStart, String rangeEnd, int from, int size) {
        log.info("Получен запрос на поиск всех событый (администратором)");
        LocalDateTime newRangeStart = null;
        if (rangeStart != null) {
            newRangeStart = DateFormatter.formatDate(rangeStart);
        }
        LocalDateTime newRangeEnd = null;
        if (rangeEnd != null) {
            newRangeEnd = DateFormatter.formatDate(rangeEnd);
        }

        if (states != null) {
            return eventRepository.findAllByAdmin(users, states, categories, newRangeStart, newRangeEnd, from, size).stream()
                    .map(EventMapper::eventToEventFullDto).collect(Collectors.toList());
        } else {
            return eventRepository.findAllByAdminAndState(users, categories, newRangeStart, newRangeEnd, from, size).stream()
                    .map(EventMapper::eventToEventFullDto).collect(Collectors.toList());
        }
    }

    @Override
    public EventFullDto updateEventById(Long eventId, UpdateEventAdminRequest updateEvent) {
        log.info("Получен запрос на обновление события с id= " + eventId + " (администратором)");
        Event event = findObjectInRepository.getEventById(eventId);
        eventAvailability(event);
        LocalDateTime publishedOn;
        if (updateEvent.getEventDate() != null) {
            publishedOn = checkEventDate(DateFormatter.formatDate(updateEvent.getEventDate()));
        } else {
            publishedOn = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        }
        if (updateEvent.getAnnotation() != null) {
            event.setAnnotation(updateEvent.getAnnotation());
        }
        if (updateEvent.getCategory() != null) {
            Category category = findObjectInRepository.getCategoryById(updateEvent.getCategory());
            event.setCategory(category);
        }
        if (updateEvent.getDescription() != null) {
            event.setDescription(updateEvent.getDescription());
        }
        if (updateEvent.getEventDate() != null) {
            event.setEventDate(DateFormatter.formatDate(updateEvent.getEventDate()));
        }
        if (updateEvent.getLocation() != null) {
            event.setLocation(LocationMapper.locationDtoToLocation(updateEvent.getLocation()));
        }
        if (updateEvent.getPaid() != null) {
            event.setPaid(updateEvent.getPaid());
        }
        if (updateEvent.getParticipantLimit() != null) {
            event.setParticipantLimit(updateEvent.getParticipantLimit());
        }
        if (updateEvent.getRequestModeration() != null) {
            event.setRequestModeration(updateEvent.getRequestModeration());
        }
        if (updateEvent.getStateAction() != null) {
            event.setState(determiningTheStatusForEvent(updateEvent.getStateAction()));
        }
        if (updateEvent.getTitle() != null) {
            event.setTitle(updateEvent.getTitle());
        }
        event.setPublishedOn(publishedOn);
        try {
            return EventMapper.eventToEventFullDto(eventRepository.save(event));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("База данных недоступна");
        } catch (Exception e) {
            throw new BadRequestException("Запрос на добавлении события " + event + " составлен не корректно ");
        }
    }

    private LocalDateTime checkEventDate(LocalDateTime eventDate) {
        LocalDateTime timeNow = LocalDateTime.now().plusHours(1L);
        if (eventDate != null && eventDate.isBefore(timeNow)) {
            throw new ForbiddenEventException("Событие должно содержать дату, которая еще не наступила. " +
                    "Value: " + eventDate);
        }
        return timeNow;
    }

    private EventState determiningTheStatusForEvent(ActionStateDto stateAction) {
        if (stateAction.equals(ActionStateDto.SEND_TO_REVIEW)) {
            return EventState.PENDING;
        } else if (stateAction.equals(ActionStateDto.CANCEL_REVIEW)) {
            return EventState.CANCELED;
        } else if (stateAction.equals(ActionStateDto.PUBLISH_EVENT)) {
            return EventState.PUBLISHED;
        } else if (stateAction.equals(ActionStateDto.REJECT_EVENT)) {
            return EventState.CANCELED;
        } else {
            throw new BadRequestException("Статус не соответствует модификатору доступа");
        }
    }

    private void eventAvailability(Event event) {
        if (event.getState().equals(EventState.PUBLISHED) || event.getState().equals(EventState.CANCELED)) {
            throw new ForbiddenEventException("Статус события не позволяет редоктировать событие, статус: " + event.getState());
        }
    }
}
