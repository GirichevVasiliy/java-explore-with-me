package ru.practicum.events.comments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.service.CommentsServicePublic;

import java.util.List;
@Service
@Slf4j
public class CommentsServicePublicImpl implements CommentsServicePublic {
    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId) {
        return null;
    }
}
