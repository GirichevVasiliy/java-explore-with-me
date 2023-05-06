package ru.practicum.events.comments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.mapper.CommentsMapper;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.model.CommentState;
import ru.practicum.events.comments.service.CommentsServicePrivate;
import ru.practicum.events.comments.storage.CommentsRepository;
import ru.practicum.events.event.model.Event;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ForbiddenEventException;
import ru.practicum.users.model.User;
import ru.practicum.util.FindObjectInRepository;

import java.util.List;

@Service
@Slf4j
public class CommentsServicePrivateImpl implements CommentsServicePrivate {
    private final FindObjectInRepository findObjectInRepository;
    private final CommentsRepository commentsRepository;
    private final ProcessingComment processingComment;

    @Autowired
    public CommentsServicePrivateImpl(FindObjectInRepository findObjectInRepository,
                                      CommentsRepository commentsRepository,
                                      ProcessingComment processingComment) {
        this.findObjectInRepository = findObjectInRepository;
        this.commentsRepository = commentsRepository;
        this.processingComment = processingComment;
    }

    @Override
    public CommentDto createComment(InputCommentDto inputCommentDto) {
        Event event = findObjectInRepository.getEventById(inputCommentDto.getEventId());
        User user = findObjectInRepository.getUserById(inputCommentDto.getUserId());
        Comment comment = CommentsMapper.createCommentPrivate(inputCommentDto, user, event);
        return CommentsMapper.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Long commentId, InputCommentDto inputCommentDto) {
        Event event = findObjectInRepository.getEventById(inputCommentDto.getEventId());
        User user = findObjectInRepository.getUserById(inputCommentDto.getUserId());
        Comment comment = findObjectInRepository.getCommentById(commentId);
        checkCommentStatus(comment);
        processingComment.checkCommentAndEvent(comment, event);
        processingComment.checkCommentOnOwner(comment, user);


        return null;
    }

    @Override
    public CommentDto getCommentById(Long commentId, Long userId) {
        return null;
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId, Long userId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {

    }
    private void checkCommentStatus(Comment comment) {
        if (comment.getState().equals(CommentState.CANCELED)) {
            throw new BadRequestException ("Комментарий с id=" + comment.getId() + " ранее был отменен");
        }
    }
}
