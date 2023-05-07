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
    List<Comment> findAllByEventIs(Event event, Pageable pageable);

    List<Comment> findAllByEventIsAndAndAuthor(Event event, User user, Pageable pageable);

    List<Comment> findAllByEventAndStateNot(Event event, CommentState state, Pageable pageable);
}
