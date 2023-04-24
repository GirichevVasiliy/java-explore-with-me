package ru.practicum.events.event.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.category.model.Category;
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

    @Query("SELECT e FROM Event e WHERE e.initiator.id IN :users AND e.state IN :states AND e.category.id IN :categories " +
            "AND e.eventDate BETWEEN :rangeStart AND :rangeEnd")
    List<Event> findAllByAdmin(List<Long> users, List<EventState> states, List<Long> categories,
                               LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);
    @Query(value = "SELECT * " +
            "FROM events  " +
            "WHERE (lower(annotation) LIKE '%'||lower(:text)||'%' OR lower(description) LIKE '%'||lower(:text)||'%') " +
            "AND (category_id IN :categories  OR :categories IS NULL) " +
            "AND (:paid IS NULL OR paid = :paid) " +
            "AND (event_date BETWEEN " +
            "to_timestamp(:rangeStart, 'yyyy-mm-dd hh24:mi:ss') AND to_timestamp(:rangeEnd, 'yyyy-mm-dd hh24:mi:ss') " +
            "OR event_date > CURRENT_TIMESTAMP) " +
            "AND (participant_limit < confirmed_requests) = :onlyAvailable " +
            "ORDER BY lower(:sort) " +
            "OFFSET :from " +
            "LIMIT :size", nativeQuery = true)
    List<Event> findAllByPublic(@Param("text") String text,
                                @Param("categories") List<Long> categories,
                                @Param("paid") Boolean paid,
                                @Param("rangeStart") String rangeStart,
                                @Param("rangeEnd") String rangeEnd,
                                @Param("onlyAvailable") Boolean onlyAvailable,
                                @Param("sort") String sort,
                                @Param("from") Integer from,
                                @Param("size") Integer size);
    Optional<Event> findEventByIdAndStateIs(Long id, EventState state);

}
