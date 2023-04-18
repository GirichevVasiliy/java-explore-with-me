package ru.practicum.events.compilation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.events.compilation.service.CompilationService;

/**
 * Публичный API для работы с подборками событий
 */
@RestController
@RequestMapping(path = "/compilations")
@Slf4j
public class CompilationController {
    private final CompilationService compilationService;
    @Autowired
    public CompilationController(CompilationService compilationService) {
        this.compilationService = compilationService;
    }
}
