package ru.practicum.events.request.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.model.EventState;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.events.request.dto.ParticipationRequestDto;
import ru.practicum.events.request.mapper.RequestMapper;
import ru.practicum.events.request.model.Request;
import ru.practicum.events.request.model.RequestStatus;
import ru.practicum.events.request.service.RequestServicePrivate;
import ru.practicum.events.request.storage.RequestRepository;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ConflictRequestException;
import ru.practicum.users.model.User;
import ru.practicum.util.FindObjectInRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RequestServicePrivateImpl implements RequestServicePrivate {
    private final RequestRepository requestRepository;
    private final FindObjectInRepository findObjectInRepository;
    private final EventRepository eventRepository;

    @Autowired
    public RequestServicePrivateImpl(RequestRepository requestRepository,
                                     FindObjectInRepository findObjectInRepository,
                                     EventRepository eventRepository) {
        this.requestRepository = requestRepository;
        this.findObjectInRepository = findObjectInRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<ParticipationRequestDto> getAllRequestsUserById(Long userId) {
        log.info("Получен запрос на получение всех запросов пользователя с id= " + userId);
        User user = findObjectInRepository.getUserById(userId);
        List<Request> requests = requestRepository.findAllByRequesterIs(user);
        return requests.stream().map(RequestMapper::requestToParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto addRequestEventById(Long userId, Long eventId) {
        log.info("Получен запрос на добавление запроса от пользователя с id= {} для события с id= {}", userId, eventId);
        User user = findObjectInRepository.getUserById(userId);
        Event event = findObjectInRepository.getEventById(eventId);
        checkEventState(event);
        checkEventOwner(user, event);
        checkParticipantLimit(event);
        checkEventRequestUser(userId, eventId);
        Request request;
        if (event.isRequestModeration()) {
            request = Request.builder()
                    .created(LocalDateTime.now())
                    .event(event)
                    .requester(user)
                    .status(RequestStatus.PENDING)
                    .build();
        } else {
            request = Request.builder()
                    .created(LocalDateTime.now())
                    .event(event)
                    .requester(user)
                    .status(RequestStatus.CONFIRMED)
                    .build();
            Long confirmedRequests = event.getConfirmedRequests();
            event.setConfirmedRequests(confirmedRequests + 1L);
        }
        try {
            eventRepository.save(event);
            return RequestMapper.requestToParticipationRequestDto(requestRepository.save(request));
        } catch (DataAccessException e) {
            throw new BadRequestException("Ошибка при работе с базой данных");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Ошибка в формировании запроса");
        }
    }

    @Override
    public ParticipationRequestDto updateRequestStatus(Long userId, Long requestId) {
        log.info("Получен запрос от пользователя с id= {} на обновление запроса с id= {}", userId, requestId);
        User user = findObjectInRepository.getUserById(userId);
        Request request = findObjectInRepository.getRequestById(requestId);
        if (!request.getRequester().equals(user)) {
            throw new ConflictRequestException("Пользователь с id= " + userId
                    + "не подавал заявку с id= " + request.getId());
        }
        request.setStatus(RequestStatus.CANCELED);
        Event event = request.getEvent();
        Long confirmedRequests = event.getConfirmedRequests();
        event.setConfirmedRequests(confirmedRequests - 1L);
        try {
            eventRepository.save(event);
            return RequestMapper.requestToParticipationRequestDto(requestRepository.save(request));
        } catch (DataAccessException e) {
            throw new BadRequestException("Ошибка при работе с базой данных");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Ошибка в формировании запроса");
        }
    }

    private void checkEventRequestUser(Long userId, Long eventId) {
        Optional<Request> request = requestRepository.findByRequesterIdAndEventId(userId, eventId);
        if (request.isPresent()) {
            throw new ConflictRequestException("Пользователь с id= " + userId
                    + "участник события с id= " + eventId);
        }
    }

    private void checkEventOwner(User user, Event event) {
        if (Objects.equals(user.getId(), event.getInitiator().getId())) {
            throw new ConflictRequestException("Пользователь с id= " + user.getId()
                    + "владелец события с id= " + event.getId() + " и не может подавать заявку на участие");
        }
    }

    private void checkEventState(Event event) {
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictRequestException("Событие с id= " + event.getId()
                    + " не опубликовано, нельзя подавать запросы на участие");
        }
    }

    private void checkParticipantLimit(Event event) {
        if (event.getParticipantLimit() == event.getConfirmedRequests() && event.getParticipantLimit() != 0) {
            throw new ConflictRequestException("Событие с id= " + event.getId()
                    + " нельзя подавать запросы на участие, превышен лимит заявок");
        }
    }
}
