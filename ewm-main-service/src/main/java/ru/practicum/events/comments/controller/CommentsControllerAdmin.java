package ru.practicum.events.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.dto.UpdateCommentAdminDto;
import ru.practicum.events.comments.service.CommentsServiceAdmin;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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
                             @Validated @RequestBody UpdateCommentAdminDto updateComment) {
        return commentsServiceAdmin.updateComment(commentId, updateComment);
    }

    @GetMapping("/{commentId}")
    CommentDto getCommentById(@PathVariable Long commentId) {
        return commentsServiceAdmin.getCommentById(commentId);
    }

    @GetMapping("/event/{eventId}")
    List<CommentDto> getAllCommentsByEventId(@PathVariable Long eventId,
                                             @PositiveOrZero @RequestParam(defaultValue = "0", required = false) Integer from,
                                             @Positive @RequestParam(defaultValue = "10", required = false) Integer size) {
        return commentsServiceAdmin.getAllCommentsByEventId(eventId, from, size);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteComment(@PathVariable Long commentId) {
        commentsServiceAdmin.deleteComment(commentId);
    }
}
