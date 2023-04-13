package ru.practicum.statsserver.stats.service;

import ru.practicum.StatsDto;

import java.util.List;

public interface StatsService {
    List<StatsDto> getStats(String start, String end, List<String> uris, boolean unique);
}
