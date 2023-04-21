package ru.practicum.events.request.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.request.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByEvent( Event event);
    List<Request> findAllByIdIsIn(List<Long> ids);
}
