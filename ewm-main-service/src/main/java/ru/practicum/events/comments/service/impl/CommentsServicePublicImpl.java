package ru.practicum.events.comments.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.mapper.CommentsMapper;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.model.CommentState;
import ru.practicum.events.comments.service.CommentsServicePublic;
import ru.practicum.events.comments.storage.CommentsRepository;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentsServicePublicImpl implements CommentsServicePublic {
    private final EventRepository eventRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServicePublicImpl(EventRepository eventRepository,
                                     CommentsRepository commentsRepository) {
        this.eventRepository = eventRepository;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId, Integer from, Integer size) {
        Event event = getEventById(eventId);
        Pageable pageable = PageRequest.of(from, size);
        List<Comment> comments = commentsRepository.findByEventAndStateIsNot(event, CommentState.CANCELED, pageable);
        return comments.stream().map(CommentsMapper::commentToCommentDto).collect(Collectors.toList());
    }

    private Event getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));
        return event;
    }
}
