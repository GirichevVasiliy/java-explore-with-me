package ru.practicum.events.comments.service;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;

import java.util.List;

public interface CommentsServiceAdmin {
    CommentDto createComment(InputCommentDto inputCommentDto);
    CommentDto updateComment(Long commentId, InputCommentDto inputCommentDto);
    CommentDto getCommentById(Long commentId);
    List<CommentDto> getAllCommentsByEventId(Long eventId);
    void deleteComment(Long commentId);
}
