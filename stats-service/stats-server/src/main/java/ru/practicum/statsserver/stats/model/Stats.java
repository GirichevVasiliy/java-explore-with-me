package ru.practicum.statsserver.stats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
    private String app; //ewm-main-service
    private String uri; // /events/1
    private Long hits; // кол-во
}
