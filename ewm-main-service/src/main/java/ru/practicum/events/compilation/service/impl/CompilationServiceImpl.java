package ru.practicum.events.compilation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.service.CompilationService;
import ru.practicum.events.compilation.storage.CompilationStorage;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CompilationServiceImpl implements CompilationService {
    private final CompilationStorage compilationStorage;

    @Autowired
    public CompilationServiceImpl(CompilationStorage compilationStorage) {
        this.compilationStorage = compilationStorage;
    }

    @Override
    public List<CompilationDto> getAllCompilations(boolean pinned, int from, int size) {
        return null;
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        return null;
    }
}
