package ru.practicum.events.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.service.CommentsServiceAdmin;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/comments/event/{eventId}")
@Slf4j
public class CommentsControllerAdmin {
    private final CommentsServiceAdmin commentsServiceAdmin;

    @Autowired
    public CommentsControllerAdmin(CommentsServiceAdmin commentsServiceAdmin) {
        this.commentsServiceAdmin = commentsServiceAdmin;
    }

    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto createComment(@NotNull @PathVariable Long eventId,
                             @NotNull @PathVariable Long userId,
                             @Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServiceAdmin.createComment(eventId, userId, inputCommentDto);
    }

    @PatchMapping("/comment/{commentId}")
    CommentDto updateComment(@NotNull @PathVariable Long eventId,
                             @NotNull @PathVariable Long commentId,
                             @Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServiceAdmin.updateComment(eventId, commentId, inputCommentDto);
    }

    @GetMapping("/comment/{commentId}")
    CommentDto getCommentById(@NotNull @PathVariable Long eventId,
                              @NotNull @PathVariable Long commentId) {
        return commentsServiceAdmin.getCommentById(eventId, commentId);
    }

    @GetMapping()
    List<CommentDto> getAllCommentsByEventId(@NotNull @PathVariable Long eventId) {
        return commentsServiceAdmin.getAllCommentsByEventId(eventId);
    }

    @DeleteMapping("/comment/{commentId}")
    void deleteComment(@NotNull @PathVariable Long eventId,
                       @NotNull @PathVariable Long commentId) {
        commentsServiceAdmin.deleteComment(eventId, commentId);
    }
}
