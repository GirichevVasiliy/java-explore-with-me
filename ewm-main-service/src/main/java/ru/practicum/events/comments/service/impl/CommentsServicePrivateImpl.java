package ru.practicum.events.comments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.mapper.CommentsMapper;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.model.CommentState;
import ru.practicum.events.comments.moderation.ModerationMessage;
import ru.practicum.events.comments.service.CommentsServicePrivate;
import ru.practicum.events.comments.storage.CommentsRepository;
import ru.practicum.events.event.model.Event;
import ru.practicum.exception.BadRequestException;
import ru.practicum.users.model.User;
import ru.practicum.util.FindObjectInRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentsServicePrivateImpl implements CommentsServicePrivate {
    private final FindObjectInRepository findObjectInRepository;
    private final CommentsRepository commentsRepository;
    private final ProcessingComment processingComment;
    private final ModerationMessage moderationMessage;

    @Autowired
    public CommentsServicePrivateImpl(FindObjectInRepository findObjectInRepository,
                                      CommentsRepository commentsRepository,
                                      ProcessingComment processingComment,
                                      ModerationMessage moderationMessage) {
        this.findObjectInRepository = findObjectInRepository;
        this.commentsRepository = commentsRepository;
        this.processingComment = processingComment;
        this.moderationMessage = moderationMessage;
    }

    @Override
    public CommentDto createComment(InputCommentDto inputCommentDto) {
        moderationMessage.moderationMessage(inputCommentDto.getText());
        Event event = findObjectInRepository.getEventById(inputCommentDto.getEventId());
        User user = findObjectInRepository.getUserById(inputCommentDto.getUserId());
        Comment comment = CommentsMapper.createComment(inputCommentDto, user, event);
        return CommentsMapper.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Long commentId, InputCommentDto inputCommentDto) {
        moderationMessage.moderationMessage(inputCommentDto.getText());
        Comment comment = findObjectInRepository.getCommentById(commentId);
        checkCommentStatus(comment);
        Event event = findObjectInRepository.getEventById(inputCommentDto.getEventId());
        User user = findObjectInRepository.getUserById(inputCommentDto.getUserId());
        processingComment.checkCommentAndEvent(comment, event);
        processingComment.checkCommentOnOwner(comment, user);
        comment.setText(inputCommentDto.getText());
        comment.setEvent(event);
        comment.setState(CommentState.UPDATE);
        comment.setCreatedOn(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return CommentsMapper.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public CommentDto getCommentById(Long commentId, Long userId) {
        Comment comment = findObjectInRepository.getCommentById(commentId);
        User user = findObjectInRepository.getUserById(userId);
        processingComment.checkCommentOnOwner(comment, user);
        return CommentsMapper.commentToCommentDto(comment);
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId, Long userId, Integer from, Integer size) {
        Event event = findObjectInRepository.getEventById(eventId);
        User user = findObjectInRepository.getUserById(userId);
        Pageable pageable = PageRequest.of(from, size);
        List<Comment> comments = commentsRepository.findAllByEventIsAndAndAuthor(event, user, pageable);
        return comments.stream().map(CommentsMapper::commentToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        User user = findObjectInRepository.getUserById(userId);
        Comment comment = findObjectInRepository.getCommentById(commentId);
        processingComment.checkCommentOnOwner(comment, user);
        commentsRepository.delete(comment);
    }

    private void checkCommentStatus(Comment comment) {
        if (comment.getState().equals(CommentState.CANCELED)) {
            throw new BadRequestException("Комментарий с id=" + comment.getId() + " ранее был отменен");
        }
    }
}
