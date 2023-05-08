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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void moderationMessage(String text) {
        List<String> newText = Stream.of(text.split(" ")).map(String::toLowerCase).collect(Collectors.toList());
        List<String> lines = List.of("мат", "брань");
        newText.retainAll(lines);
        if (!newText.isEmpty()) {
            throw new BadRequestException("Комментарий является недопустимым и отклонен");
        }
    }
}
