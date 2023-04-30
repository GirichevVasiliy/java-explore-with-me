package ru.practicum.explorewithme.stats.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explorewithme.stats.dto.HitDto;
import ru.practicum.explorewithme.stats.dto.StatsDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatsClient {
    private final WebClient client;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String URL = "http://localhost:9090";

    public StatsClient(String serverUrl) {
        this.client = WebClient.create(serverUrl);
    }

    public void hitRequest(HitDto hitDto) {
        client.post()
                .uri(URL + "/hit")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(hitDto)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/stats")
                        .queryParam("start", start.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                        .queryParam("end", end.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                        .queryParam("uris", uris)
                        .queryParam("unique", unique)
                        .build())
                .retrieve()
                .bodyToFlux(StatsDto.class)
                .collectList()
                .block();
    }
}
