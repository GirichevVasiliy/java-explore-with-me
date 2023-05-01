package ru.practicum.explorewithme.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@AllArgsConstructor
@Jacksonized
public class StatsDto {
    String app; //ewm-main-service
    String uri; // /events/1
    Long hits; // кол-во
}
