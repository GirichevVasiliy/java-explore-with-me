package ru.practicum.events.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.service.CommentsServicePrivate;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/private/comments/event/{eventId}")
@Slf4j
public class CommentsControllerPrivate {
    private final CommentsServicePrivate commentsServicePrivate;
@Autowired
    public CommentsControllerPrivate(CommentsServicePrivate commentsServicePrivate) {
        this.commentsServicePrivate = commentsServicePrivate;
    }

    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto createComment(@NotNull @PathVariable Long eventId,
                             @NotNull @PathVariable Long userId,
                             @Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServicePrivate.createComment(eventId, userId, inputCommentDto);
    }

    @PatchMapping("/comment/{commentId}/user/{userId}")
    CommentDto updateComment(@NotNull @PathVariable Long eventId,
                             @NotNull @PathVariable Long commentId,
                             @NotNull @PathVariable Long userId,
                             @Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServicePrivate.updateComment(eventId, commentId, userId, inputCommentDto);
    }

    @GetMapping("/comment/{commentId}/user/{userId}")
    CommentDto getCommentById(@NotNull @PathVariable Long eventId,
                              @NotNull @PathVariable Long commentId,
                              @NotNull @PathVariable Long userId) {
        return commentsServicePrivate.getCommentById(eventId, commentId, userId);
    }

    @GetMapping("/user/{userId}")
    List<CommentDto> getAllCommentsByEventId(@NotNull @PathVariable Long eventId,
                                             @NotNull @PathVariable Long userId) {
        return commentsServicePrivate.getAllCommentsByEventId(eventId, userId);
    }

    @DeleteMapping("/comment/{commentId}/user/{userId}")
    void deleteComment(@NotNull @PathVariable Long eventId,
                       @NotNull @PathVariable Long commentId,
                       @NotNull @PathVariable Long userId) {
        commentsServicePrivate.deleteComment(eventId, commentId, userId);
    }
}
