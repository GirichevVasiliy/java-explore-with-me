package ru.practicum.statsserver.hit.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.statsserver.hit.model.Hit;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {

}
