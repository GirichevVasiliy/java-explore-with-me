package ru.practicum.events.comments.service;

import ru.practicum.events.comments.dto.CommentDto;

import java.util.List;

public interface  CommentsServicePublic {
    List<CommentDto> getAllCommentsByEventId(Long eventId);
}
