package ru.practicum.statsserver.stats.storage;

import ru.practicum.statsserver.stats.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository {
    List<Stats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
