package ru.practicum.statsserver.hit.mapper;

import ru.practicum.HitDto;
import ru.practicum.statsserver.hit.model.Hit;
import ru.practicum.statsserver.util.DateFormatter;

public class HitMapper {
    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .ip(hitDto.getIp())
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .timestamp(DateFormatter.formatDate(hitDto.getTimestamp()))
                .build();
    }
}
