package ru.practicum.events.comments.service;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.dto.UpdateCommentAdminDto;

import java.util.List;

public interface CommentsServiceAdmin {
    CommentDto createComment(InputCommentDto inputCommentDto);

    CommentDto updateComment(Long commentId, UpdateCommentAdminDto updateComment);

    CommentDto getCommentById(Long commentId);

    List<CommentDto> getAllCommentsByEventId(Long eventId, Integer from, Integer size);

    void deleteComment(Long commentId);
}
