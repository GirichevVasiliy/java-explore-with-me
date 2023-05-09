package ru.practicum.events.comments.service.impl;

import lombok.experimental.UtilityClass;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.event.model.Event;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ForbiddenEventException;
import ru.practicum.users.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class CommentUtil {
    public void checkCommentOnEvent(Comment comment, Event event) {
        if (!comment.getEvent().getId().equals(event.getId())) {
            throw new ForbiddenEventException("Комментарий с id=" + comment.getId() + " не принадлежит событию с id=" + event.getId());
        }
    }

    public void checkCommentOnOwner(Comment comment, User user) {
        if (!comment.getAuthor().getId().equals(user.getId())) {
            throw new ForbiddenEventException("Комментарий с id=" + comment.getId() + " не принадлежит пользователю с id=" + user.getId());
        }
    }

    public void moderateMessage(String text) {
        List<String> newText = Stream.of(text.split(" ")).map(String::toLowerCase).collect(Collectors.toList());
        List<String> lines = List.of("мат", "брань");
        newText.retainAll(lines);
        if (!newText.isEmpty()) {
            throw new BadRequestException("Комментарий является недопустимым и отклонен");
        }
    }
}
