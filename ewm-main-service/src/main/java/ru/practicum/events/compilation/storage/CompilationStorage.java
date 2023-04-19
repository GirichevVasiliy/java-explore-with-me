package ru.practicum.events.compilation.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.events.compilation.model.Compilation;
@Repository
public interface CompilationStorage extends JpaRepository<Compilation, Long> {
}
