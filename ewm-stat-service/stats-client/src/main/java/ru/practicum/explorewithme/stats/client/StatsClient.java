package ru.practicum.explorewithme.stats.client;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.practicum.explorewithme.stats.dto.HitDto;
import ru.practicum.explorewithme.stats.dto.StatsDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class StatsClient extends BaseClient {
    private static final Integer START_DATE_OFFSET = 6;
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public StatsClient(String serverUrl) {
        super(serverUrl);
    }

    public void hitRequest(HitDto hitDto) {
        post("/hit", hitDto);
    }

    public Optional<Long> statsRequest(Long eventId) {
        String start = LocalDateTime.now().minusMonths(START_DATE_OFFSET).format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("start", start);
        parameters.add("end", end);
        parameters.add("uris", String.format("/events/%d", eventId));

        List<StatsDto> stats = get("/stats", parameters);
        if (stats != null) {
            if (stats.isEmpty()) {
                return Optional.empty();
            }
            StatsDto viewStats = stats.get(0);
            if (viewStats != null) {
                return Optional.of(viewStats.getHits());
            }
        }
        return Optional.empty();
    }
}
