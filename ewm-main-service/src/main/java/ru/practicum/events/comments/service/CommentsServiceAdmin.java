package ru.practicum.events.comments.service;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;

import java.util.List;

public interface CommentsServiceAdmin {
    CommentDto createComment(Long eventId, Long userId, InputCommentDto inputCommentDto);
    CommentDto updateComment(Long eventId, Long commentId, InputCommentDto inputCommentDto);
    CommentDto getCommentById(Long eventId, Long commentId);
    List<CommentDto> getAllCommentsByEventId(Long eventId);
    void deleteComment(Long eventId, Long commentId);
}
