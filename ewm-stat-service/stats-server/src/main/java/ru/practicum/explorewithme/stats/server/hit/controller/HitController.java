package ru.practicum.explorewithme.stats.server.hit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.stats.dto.HitDto;
import ru.practicum.explorewithme.stats.server.hit.service.HitService;

@RestController
@RequestMapping(path = "/hit")
@Slf4j
public class HitController {
    private final HitService hitService;

    @Autowired
    public HitController(HitService hitService) {
        this.hitService = hitService;
    }

    @PostMapping
    public void addHit(@Validated @RequestBody HitDto hitDto) {
        log.info("Add hitDto {}", hitDto);
        hitService.addHit(hitDto);
    }

}
