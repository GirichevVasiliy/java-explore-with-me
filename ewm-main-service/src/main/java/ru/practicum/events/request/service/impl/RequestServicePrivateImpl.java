package ru.practicum.events.request.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.request.dto.ParticipationRequestDto;
import ru.practicum.events.request.service.RequestServicePrivate;
import ru.practicum.events.request.storage.RequestRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class RequestServicePrivateImpl implements RequestServicePrivate {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServicePrivateImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<ParticipationRequestDto> getAllRequestsUserById(Long userId) {
        return null;
    }

    @Transactional
    @Override
    public ParticipationRequestDto addRequestEventById(Long userId, Long eventId) {
        return null;
    }

    @Transactional
    @Override
    public ParticipationRequestDto updateRequestById(Long userId, Long requestId) {
        return null;
    }
}
