package ru.practicum.events.comments.service;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;

import java.util.List;

public interface CommentsServicePrivate {
    CommentDto createComment(Long eventId, Long userId, InputCommentDto inputCommentDto);
    CommentDto updateComment(Long eventId, Long commentId, Long userId, InputCommentDto inputCommentDto);
    CommentDto getCommentById(Long eventId, Long commentId, Long userId);
    List<CommentDto> getAllCommentsByEventId(Long eventId, Long userId);
    void deleteComment(Long eventId, Long commentId, Long userId);
}
