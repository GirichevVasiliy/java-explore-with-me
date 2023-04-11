package ru.practicum.statsserver.hit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.HitDto;
import ru.practicum.statsserver.hit.service.HitService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/hit")
@Slf4j
@Validated
public class HitController {
    private final HitService hitService;

    @Autowired
    public HitController(HitService hitService) {
        this.hitService = hitService;
    }

    @PostMapping
    public void addHit(@Valid @RequestBody HitDto hitDto) {
        log.info("Add hitDto {}", hitDto);
        hitService.addHit(hitDto);
    }

}
