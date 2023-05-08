package ru.practicum.events.comments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.mapper.CommentsMapper;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.model.CommentState;
import ru.practicum.events.comments.service.CommentsServicePublic;
import ru.practicum.events.comments.storage.CommentsRepository;
import ru.practicum.events.event.model.Event;
import ru.practicum.util.FindObjectInRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentsServicePublicImpl implements CommentsServicePublic {
    private final FindObjectInRepository findObjectInRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServicePublicImpl(FindObjectInRepository findObjectInRepository, CommentsRepository commentsRepository) {
        this.findObjectInRepository = findObjectInRepository;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId, Integer from, Integer size) {
        Event event = findObjectInRepository.getEventById(eventId);
        Pageable pageable = PageRequest.of(from, size);
        List<Comment> comments = commentsRepository.findAllByEventIsAndStateIsNot(event, CommentState.CANCELED, pageable);
        return comments.stream().map(CommentsMapper::commentToCommentDto).collect(Collectors.toList());
    }
}
