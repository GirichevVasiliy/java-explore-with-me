package ru.practicum.events.comments.service;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;

import java.util.List;

public interface CommentsServicePrivate {
    CommentDto createComment(InputCommentDto inputCommentDto);

    CommentDto updateComment(Long commentId, InputCommentDto inputCommentDto);

    CommentDto getCommentById(Long commentId, Long userId);

    List<CommentDto> getAllCommentsByEventId(Long eventId, Long userId, Integer from, Integer size);

    void deleteComment(Long commentId, Long userId);
}
