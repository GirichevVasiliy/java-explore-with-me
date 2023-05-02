package ru.practicum.events.compilation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.service.CompilationServicePublic;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Публичный API для работы с подборками событий
 */
@RestController
@RequestMapping(path = "/compilations")
@Slf4j
@Validated
public class CompilationControllerPublic {
    private final CompilationServicePublic compilationService;

    @Autowired
    public CompilationControllerPublic(CompilationServicePublic compilationService) {
        this.compilationService = compilationService;
    }

    /**
     * Получение подборок событий
     */
    @GetMapping()
    public List<CompilationDto> getAllCompilations(@RequestParam(required = false) Boolean pinned,
                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {
        return compilationService.getAllCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable Long compId) {
        return compilationService.getCompilationById(compId);
    }
}
