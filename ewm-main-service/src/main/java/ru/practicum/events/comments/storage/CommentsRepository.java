package ru.practicum.events.comments.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.events.comments.model.Comment;
import ru.practicum.events.comments.model.CommentState;
import ru.practicum.events.event.model.Event;
import ru.practicum.users.model.User;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByEvent(Event event, Pageable pageable);

    List<Comment> findByEventAndAuthor(Event event, User user, Pageable pageable);

    List<Comment> findByEventAndStateIsNot(Event event, CommentState state, Pageable pageable);
}
