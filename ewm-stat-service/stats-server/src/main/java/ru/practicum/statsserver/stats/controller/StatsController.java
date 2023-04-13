package ru.practicum.statsserver.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.HitDto;
import ru.practicum.StatsDto;
import ru.practicum.statsserver.hit.service.HitService;
import ru.practicum.statsserver.stats.service.StatsService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/stats")
@Slf4j
public class StatsController {
    private final StatsService service;
    private final HitService hitService;

    @Autowired
    public StatsController(StatsService service, HitService hitService) {
        this.service = service;
        this.hitService = hitService;
    }

    @GetMapping
    public List<StatsDto> getStats(@RequestParam String start,
                                   @RequestParam String end,
                                   @RequestParam(required = false) List<String> uris,
                                   @RequestParam(defaultValue = "false") Boolean unique,
                                   HttpServletRequest request) {
        List<StatsDto> list = service.getStats(start, end, uris, unique);
        HitDto hitDto = HitDto.builder()
                .uri(request.getRequestURI())
                .app(request.getServerName())
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        hitService.addHit(hitDto);
        return list;
    }
}
