package ru.practicum.events.comments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.events.comments.dto.CommentDto;
import ru.practicum.events.comments.dto.CommentStateDto;
import ru.practicum.events.comments.dto.InputCommentDto;
import ru.practicum.events.comments.dto.UpdateCommentAdminDto;
import ru.practicum.events.comments.mapper.CommentsMapper;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.service.CommentsServiceAdmin;
import ru.practicum.events.comments.storage.CommentsRepository;
import ru.practicum.events.event.model.Event;
import ru.practicum.exception.BadRequestException;
import ru.practicum.users.model.User;
import ru.practicum.util.FindObjectInRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentsServiceAdminImpl implements CommentsServiceAdmin {
    private final FindObjectInRepository findObjectInRepository;
    private final CommentsRepository commentsRepository;
    private final ProcessingComment processingComment;

    @Autowired
    public CommentsServiceAdminImpl(FindObjectInRepository findObjectInRepository,
                                    CommentsRepository commentsRepository,
                                    ProcessingComment processingComment) {
        this.findObjectInRepository = findObjectInRepository;
        this.commentsRepository = commentsRepository;
        this.processingComment = processingComment;
    }

    @Override
    public CommentDto createComment(InputCommentDto inputCommentDto) {
        Event event = findObjectInRepository.getEventById(inputCommentDto.getEventId());
        User admin = findObjectInRepository.getUserById(inputCommentDto.getUserId());
        Comment comment = CommentsMapper.createComment(inputCommentDto, admin, event);
        return CommentsMapper.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Long commentId, UpdateCommentAdminDto updateComment) {
        Event event = findObjectInRepository.getEventById(updateComment.getEventId());
        findObjectInRepository.getUserById(updateComment.getUserId());
        Comment comment = findObjectInRepository.getCommentById(commentId);
        processingComment.checkCommentAndEvent(comment, event);
        if (updateComment.getText() != null && !updateComment.getText().isBlank()) {
            comment.setText(updateComment.getText());
        }
        if (updateComment.getCommentStateDto() != null) {
            addCommentStatusAdmin(updateComment, comment);
        }
        return CommentsMapper.commentToCommentDto(commentsRepository.save(comment));
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        return CommentsMapper.commentToCommentDto(findObjectInRepository.getCommentById(commentId));
    }

    @Override
    public List<CommentDto> getAllCommentsByEventId(Long eventId, Integer from, Integer size) {
        Event event = findObjectInRepository.getEventById(eventId);
        Pageable pageable = PageRequest.of(from, size);
        List<Comment> comments = commentsRepository.findAllByEventIs(event, pageable);
        return comments.stream().map(CommentsMapper::commentToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = findObjectInRepository.getCommentById(commentId);
        commentsRepository.delete(comment);
    }

    private void addCommentStatusAdmin(UpdateCommentAdminDto updateComment, Comment comment) {
        if (updateComment.getCommentStateDto().equals(CommentStateDto.CANCELED)) {
            comment.setState(processingComment.determiningTheStatusForComment(updateComment.getCommentStateDto()));
        }
        if (updateComment.getCommentStateDto().equals(CommentStateDto.PUBLISHED)) {
            comment.setState(processingComment.determiningTheStatusForComment(updateComment.getCommentStateDto()));
        }
        if (updateComment.getCommentStateDto().equals(CommentStateDto.UPDATE)) {
            comment.setState(processingComment.determiningTheStatusForComment(updateComment.getCommentStateDto()));
        } else {
            throw new BadRequestException("Статус не соответствует модификатору доступа");
        }
    }
}
