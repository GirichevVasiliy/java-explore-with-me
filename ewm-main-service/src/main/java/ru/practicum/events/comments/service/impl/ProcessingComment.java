package ru.practicum.events.comments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.events.comments.dto.CommentStateDto;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.model.CommentState;
import ru.practicum.events.event.model.Event;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ForbiddenEventException;
import ru.practicum.users.model.User;

@Service
@Slf4j
public class ProcessingComment {
    public void checkCommentAndEvent(Comment comment, Event event) {
        if (!comment.getEvent().equals(event)) {
            throw new ForbiddenEventException("Комментарий с id=" + comment.getId() + " не принадлежит событию с id=" + event.getId());
        }
    }

    public void checkCommentOnOwner(Comment comment, User user) {
        if (!comment.getAuthor().getId().equals(user.getId())) {
            throw new ForbiddenEventException("Комментарий с id=" + comment.getId() + " не принадлежит пользователю с id=" + user.getId());
        }
    }

    public CommentState determiningTheStatusForComment(CommentStateDto commentStateDto) {
        if (commentStateDto.name().equals(CommentState.UPDATE.name())) {
            return CommentState.UPDATE;
        }
        if (commentStateDto.name().equals(CommentState.PUBLISHED.name())) {
            return CommentState.PUBLISHED;
        }
        if (commentStateDto.name().equals(CommentState.CANCELED.name())) {
            return CommentState.CANCELED;
        } else {
            throw new BadRequestException("Статус не соответствует модификатору доступа");
        }
    }
}
