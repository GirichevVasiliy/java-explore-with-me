package ru.practicum.events.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.service.CommentsServiceAdmin;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/comments")
@Slf4j
public class CommentsControllerAdmin {
    private final CommentsServiceAdmin commentsServiceAdmin;

    @Autowired
    public CommentsControllerAdmin(CommentsServiceAdmin commentsServiceAdmin) {
        this.commentsServiceAdmin = commentsServiceAdmin;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto createComment(@Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServiceAdmin.createComment(inputCommentDto);
    }

    @PatchMapping("/{commentId}")
    CommentDto updateComment(@PathVariable Long commentId,
                             @Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServiceAdmin.updateComment(commentId, inputCommentDto);
    }

    @GetMapping("/{commentId}")
    CommentDto getCommentById(@PathVariable Long commentId) {
        return commentsServiceAdmin.getCommentById(commentId);
    }

    @GetMapping("/{eventId}")
    List<CommentDto> getAllCommentsByEventId(@PathVariable Long eventId) {
        return commentsServiceAdmin.getAllCommentsByEventId(eventId);
    }

    @DeleteMapping("/{commentId}")
    void deleteComment(@PathVariable Long commentId) {
        commentsServiceAdmin.deleteComment(commentId);
    }
}
