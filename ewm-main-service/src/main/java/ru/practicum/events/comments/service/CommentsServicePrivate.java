package ru.practicum.events.comments.service;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;

import java.util.List;

public interface CommentsServicePrivate {
    CommentDto createComment(Long eventId, Long userId, InputCommentDto inputCommentDto);
    CommentDto updateComment(Long eventId, Long userId, InputCommentDto inputCommentDto);
    List<CommentDto> getAllComments(Long eventId, Long userId);
    void deleteComment(Long eventId, Long userId);
}
