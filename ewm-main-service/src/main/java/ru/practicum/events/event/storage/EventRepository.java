package ru.practicum.events.event.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.events.event.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
