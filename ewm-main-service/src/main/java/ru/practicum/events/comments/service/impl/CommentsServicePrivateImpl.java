package ru.practicum.events.comments.service.impl;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.service.CommentsServicePrivate;

import java.util.List;

public class CommentsServicePrivateImpl implements CommentsServicePrivate {
    @Override
    public CommentDto createComment(Long eventId, Long userId, InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public CommentDto updateComment(Long eventId, Long userId, InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public List<CommentDto> getAllComments(Long eventId, Long userId) {
        return null;
    }

    @Override
    public void deleteComment(Long eventId, Long userId) {

    }
}
