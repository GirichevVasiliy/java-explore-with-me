package ru.practicum.statsserver.hit.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.statsserver.hit.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {
    @Query("SELECT h FROM Hit h WHERE h.timestamp BETWEEN :start AND :end AND h.uri ilike :uri")
    List<Hit> getAllHits(LocalDateTime start, LocalDateTime end, List<String> uri);

}
