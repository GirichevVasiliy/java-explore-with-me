package ru.practicum.events.comments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.service.CommentsServicePublic;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/comments/event/{eventId}")
@Slf4j
public class CommentsControllerPublic {
    private final CommentsServicePublic commentsServicePublic;

    @Autowired
    public CommentsControllerPublic(CommentsServicePublic commentsServicePublic) {
        this.commentsServicePublic = commentsServicePublic;
    }
    @GetMapping()
    List<CommentDto> getAllCommentsByEventId(@NotNull @PathVariable Long eventId) {
        return commentsServicePublic.getAllCommentsByEventId(eventId);
    }
}
