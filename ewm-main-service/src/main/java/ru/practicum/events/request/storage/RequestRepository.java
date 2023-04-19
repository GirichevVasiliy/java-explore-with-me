package ru.practicum.events.request.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.events.request.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
