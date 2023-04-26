package ru.practicum.events.event.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.category.model.Category;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.dto.NewEventDto;
import ru.practicum.events.event.dto.UpdateEventUserRequest;
import ru.practicum.events.event.dto.stateDto.ActionStateDto;
import ru.practicum.events.event.mapper.EventMapper;
import ru.practicum.events.event.mapper.LocationMapper;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.model.EventState;
import ru.practicum.events.event.service.EventServicePrivate;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.events.request.dto.EventRequestStatusUpdateRequest;
import ru.practicum.events.request.dto.EventRequestStatusUpdateResult;
import ru.practicum.events.request.dto.ParticipationRequestDto;
import ru.practicum.events.request.mapper.RequestMapper;
import ru.practicum.events.request.model.Request;
import ru.practicum.events.request.model.RequestStatus;
import ru.practicum.events.request.storage.RequestRepository;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ForbiddenEventException;
import ru.practicum.exception.ResourceNotFoundException;
import ru.practicum.users.model.User;
import ru.practicum.util.FindObjectInRepository;
import ru.practicum.util.util.DateFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServicePrivateImpl implements EventServicePrivate {
    private final EventRepository eventRepository;
    private final FindObjectInRepository findObjectInRepository;
    private final RequestRepository requestRepository;

    @Autowired
    public EventServicePrivateImpl(EventRepository eventRepository,
                                   FindObjectInRepository findObjectInRepository,
                                   RequestRepository requestRepository) {
        this.eventRepository = eventRepository;
        this.findObjectInRepository = findObjectInRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public List<EventShortDto> getAllPrivateEventsByUserId(Long userId, int from, int size) {
        findObjectInRepository.getUserById(userId);
        Pageable pageable = PageRequest.of(from, size);
        return eventRepository.findAllByInitiatorId(userId, pageable).stream()
                .map(EventMapper::eventToeventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventFullDto addPrivateEventByUserId(Long userId, NewEventDto newEventDto) {
        checkEventDate(DateFormatter.formatDate(newEventDto.getEventDate()));
        User user = findObjectInRepository.getUserById(userId);
        Category category = findObjectInRepository.getCategoryById(newEventDto.getCategory());
        Long views = 0L;
        Long confirmedRequests = 0L;
        Event event = EventMapper.newEventDtoToCreateEvent(newEventDto, user, category, views, confirmedRequests);
        return getEventFullDto(event);
    }

    @Override
    public EventFullDto getPrivateEventByIdAndByUserId(Long userId, Long eventId) {
        User user = findObjectInRepository.getUserById(userId);
        Event event = findObjectInRepository.getEventById(eventId);
        checkOwnerEvent(event, user);
        return EventMapper.eventToEventFullDto(event);
    }

    @Override
    public EventFullDto updatePrivateEventByIdAndByUserId(Long userId, Long eventId, UpdateEventUserRequest updateEvent) {
        if (updateEvent.getEventDate() != null){
            checkEventDate(DateFormatter.formatDate(updateEvent.getEventDate()));
        }
        Event event = findObjectInRepository.getEventById(eventId);
        User user = findObjectInRepository.getUserById(userId);
        checkOwnerEvent(event, user);
        eventAvailability(event);
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
        return getEventFullDto(event);
    }

    @Override
    public List<ParticipationRequestDto> getAllPrivateEventsByRequests(Long userId, Long eventId) {
        try {
            Event event = findObjectInRepository.getEventById(eventId);
            User user = findObjectInRepository.getUserById(userId);
            checkOwnerEvent(event, user);
            List<Request> requests = requestRepository.findAllByEvent(event);
            return requests.stream().map(RequestMapper::requestToParticipationRequestDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BadRequestException("Некорректный запрос получения списка запросов на участие в текущем событии");
        }
    }

    @Override
    public EventRequestStatusUpdateResult updateEventRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest request) {
        Event event = findObjectInRepository.getEventById(eventId);
        User user = findObjectInRepository.getUserById(userId);
        //eventAvailability(event);
        checkOwnerEvent(event, user);
        if (event.getParticipantLimit() == event.getConfirmedRequests()) {
            throw new ForbiddenEventException("Достигнут лимит по заявкам на данное событие с id= " + eventId);
        } else if (event.getParticipantLimit() == 0 || !event.isRequestModeration()) {
            EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult(new ArrayList<>(), new ArrayList<>());
            log.info("Подтверждение заявок не требуется");
            List<Request> requests = getAllRequestsContainsIds(request.getRequestIds());
            List<ParticipationRequestDto> confirmRequests = addConfirmAllRequests(requests);
            result.getConfirmedRequests().addAll(confirmRequests);
            updateConfirmedRequests(confirmRequests.size(), event);
            return result;
        } else {
            List<Request> requests = getAllRequestsContainsIds(request.getRequestIds());
            return considerationOfRequests(event, requests);
        }
    }

    private void checkEventDate(LocalDateTime eventDate) {
        if (eventDate != null) {
            LocalDateTime timeNow = LocalDateTime.now().plusHours(2L);
            if (eventDate.isBefore(timeNow)) {
                throw new ForbiddenEventException("Событие должно содержать дату, которая еще не наступила. " +
                        "Value: " + eventDate);
            }
        }
    }

    private void checkOwnerEvent(Event event, User user) {
        if (!Objects.equals(event.getInitiator().getId(), user.getId())) {
            throw new ForbiddenEventException("Событие с id=" + event.getId() + " не принадлежит пользователю с id=" + user.getId());
        }
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
        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenEventException("Статус события не позволяет редоктировать событие, статус: " + event.getState());
        }
    }

    private List<ParticipationRequestDto> addConfirmAllRequests(List<Request> requests) {
        List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        for (Request req : requests) {
            req.setStatus(RequestStatus.CONFIRMED);
            requestRepository.save(req);
            confirmedRequests.add(RequestMapper.requestToParticipationRequestDto(req));
        }
        return confirmedRequests;
    }

    private List<Request> getAllRequestsContainsIds(List<Long> requestIds) {
        return requestRepository.findAllByIdIsIn(requestIds);
    }

    private void updateConfirmedRequests(Integer newConfirmedRequests, Event event) {
        Long sum = event.getConfirmedRequests() + newConfirmedRequests;
        event.setConfirmedRequests(sum);
        eventRepository.save(event);
    }

    private EventRequestStatusUpdateResult considerationOfRequests(Event event, List<Request> requests) {
        List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
        Iterator<Request> i = requests.iterator();
        while (i.hasNext()) {
            Request req = i.next();
            if (event.getConfirmedRequests() <= event.getParticipantLimit()) {
                req.setStatus(RequestStatus.CONFIRMED);
                confirmedRequests.add(RequestMapper.requestToParticipationRequestDto(req));
                requestRepository.save(req);
                event.setConfirmedRequests(event.getConfirmedRequests() + 1L);
            } else {
                req.setStatus(RequestStatus.REJECTED);
                rejectedRequests.add(RequestMapper.requestToParticipationRequestDto(req));
                requestRepository.save(req);
            }
        }
        eventRepository.save(event);
        EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult(confirmedRequests, rejectedRequests);
        return result;
    }

    private EventFullDto getEventFullDto(Event event) {
        try {
            return EventMapper.eventToEventFullDto(eventRepository.save(event));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("База данных недоступна");
        } catch (Exception e) {
            throw new BadRequestException("Запрос на добавлении события " + event + " составлен не корректно ");
        }
    }
}

