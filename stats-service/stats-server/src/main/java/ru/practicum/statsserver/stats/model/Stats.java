package ru.practicum.statsserver.stats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.statsserver.hit.model.Hit;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    private String app; //ewm-main-service

    private String uri; // /events/1

    private List<Hit> hits; // кол-во
}
