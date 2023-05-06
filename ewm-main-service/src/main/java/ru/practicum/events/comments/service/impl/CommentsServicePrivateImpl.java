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
    public CommentDto createComment(InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public CommentDto updateComment(Long commentId, InputCommentDto inputCommentDto) {
        return null;
    }

    @Override
    public CommentDto getCommentById(Long commentId, Long userId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId, Long userId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {

    }
}
