package ru.practicum.events.comments.service.impl;

import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.service.CommentsServiceAdmin;

import java.util.List;

public class CommentsServiceAdminImpl implements CommentsServiceAdmin {


    @Override
    public CommentDto createComment(Long eventId, Long userId, InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public CommentDto updateComment(Long eventId, Long commentId, InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public CommentDto getCommentById(Long eventId, Long commentId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllComments(Long eventId) {
        return null;
    }

    @Override
    public void deleteComment(Long eventId) {

    }
}
