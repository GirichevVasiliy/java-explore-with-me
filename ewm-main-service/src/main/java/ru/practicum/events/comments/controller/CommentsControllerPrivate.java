package ru.practicum.events.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.service.CommentsServicePrivate;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/private/comments")
@Slf4j
public class CommentsControllerPrivate {
    private final CommentsServicePrivate commentsServicePrivate;

    @Autowired
    public CommentsControllerPrivate(CommentsServicePrivate commentsServicePrivate) {
        this.commentsServicePrivate = commentsServicePrivate;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto createComment(@Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServicePrivate.createComment(inputCommentDto);
    }

    @PatchMapping("/{commentId}")
    CommentDto updateComment(@PathVariable Long commentId,
                             @Validated @RequestBody InputCommentDto inputCommentDto) {
        return commentsServicePrivate.updateComment(commentId, inputCommentDto);
    }

    @GetMapping("/{commentId}/user/{userId}")
    CommentDto getCommentById(@PathVariable Long commentId,
                              @PathVariable Long userId) {
        return commentsServicePrivate.getCommentById(commentId, userId);
    }

    @GetMapping("/event/{eventId}/user/{userId}")
    List<CommentDto> getAllCommentsByEventId(@PathVariable Long eventId,
                                             @PathVariable Long userId,
                                             @PositiveOrZero @RequestParam(defaultValue = "0", required = false) Integer from,
                                             @Positive @RequestParam(defaultValue = "10", required = false) Integer size) {
        return commentsServicePrivate.getAllCommentsByEventId(eventId, userId, from, size);
    }

    @DeleteMapping("/{commentId}/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteComment(@PathVariable Long commentId,
                       @PathVariable Long userId) {
        commentsServicePrivate.deleteComment(commentId, userId);
    }
}
