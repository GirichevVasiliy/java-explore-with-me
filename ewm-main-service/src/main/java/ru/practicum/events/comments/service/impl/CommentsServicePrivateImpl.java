package ru.practicum.events.comments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.service.CommentsServicePrivate;

import java.util.List;
@Service
@Slf4j
public class CommentsServicePrivateImpl implements CommentsServicePrivate {

    @Override
    public CommentDto createComment(Long eventId, Long userId, InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public CommentDto updateComment(Long eventId, Long commentId, Long userId, InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public CommentDto getCommentById(Long eventId, Long commentId, Long userId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId, Long userId) {
        return null;
    }

    @Override
    public void deleteComment(Long eventId, Long commentId, Long userId) {

    }
}
