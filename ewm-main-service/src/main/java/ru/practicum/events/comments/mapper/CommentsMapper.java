package ru.practicum.events.comments.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.model.CommentState;
import ru.practicum.events.event.mapper.EventMapper;
import ru.practicum.events.event.model.Event;
import ru.practicum.users.mapper.UserMapper;
import ru.practicum.users.model.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class CommentsMapper {
    public Comment createComment(InputCommentDto inputCommentDto, User author, Event event) {
        return Comment.builder()
                .text(inputCommentDto.getText())
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .author(author)
                .event(event)
                .state(CommentState.PUBLISHED)
                .build();
    }

    public CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .createdOn(comment.getCreatedOn())
                .author(UserMapper.userToUserShortDto(comment.getAuthor()))
                .event(EventMapper.eventToEventCommentDto(comment.getEvent()))
                .state(comment.getState().name())
                .build();

    }
}
