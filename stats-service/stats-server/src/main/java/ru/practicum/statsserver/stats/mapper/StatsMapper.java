package ru.practicum.statsserver.stats.mapper;

import ru.practicum.StatsDto;
import ru.practicum.statsserver.stats.model.Stats;

public class StatsMapper {
    public static StatsDto toStatsDto(Stats stats){
        return StatsDto.builder()
                .app(stats.getApp())
                .uri(stats.getUri())
                .hits(stats.getHits().size())
                .build();
    }
}
