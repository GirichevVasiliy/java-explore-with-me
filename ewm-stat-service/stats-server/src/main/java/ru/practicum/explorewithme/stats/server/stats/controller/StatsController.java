package ru.practicum.explorewithme.stats.server.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.stats.dto.StatsDto;
import ru.practicum.explorewithme.stats.server.hit.service.HitService;
import ru.practicum.explorewithme.stats.server.stats.service.StatsService;

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
                                   @RequestParam(defaultValue = "false") Boolean unique) {
        List<StatsDto> list = service.getStats(start, end, uris, unique);
        return list;
    }
}
