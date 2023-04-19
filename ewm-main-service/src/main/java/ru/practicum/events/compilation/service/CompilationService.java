package ru.practicum.events.compilation.service;

import ru.practicum.events.compilation.dto.CompilationDto;

import java.util.List;

public interface CompilationService {
    List<CompilationDto> getAllCompilations(boolean pinned, int from, int size);
    CompilationDto getCompilationById(Long compId);
}
