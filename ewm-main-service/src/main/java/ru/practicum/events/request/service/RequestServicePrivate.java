package ru.practicum.events.request.service;

import ru.practicum.events.request.dto.ParticipationRequestDto;

import java.util.List;

public interface RequestServicePrivate {
    List<ParticipationRequestDto> getAllRequestsUserById(Long userId);
}
