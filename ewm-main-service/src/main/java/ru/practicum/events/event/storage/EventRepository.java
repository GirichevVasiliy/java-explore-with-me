package ru.practicum.events.event.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.category.model.Category;
import ru.practicum.events.event.dto.stateDto.EventStateDto;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.model.EventState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByCategoryIs(Category category);
    Set<Event> findAllByIdIsIn(List<Long> id);
    List<Event> findAllByInitiatorId(Long userId, Pageable pageable);
    @Query("SELECT e FROM Event e WHERE e.initiator.id IN :users AND e.state IN :states AND e.category.id IN :categories AND e.eventDate BETWEEN :rangeStart AND :rangeEnd")
    List<Event> findAllByAdmin(List<Long> users, List<EventState> states, List<Long> categories,
                               LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);
}
