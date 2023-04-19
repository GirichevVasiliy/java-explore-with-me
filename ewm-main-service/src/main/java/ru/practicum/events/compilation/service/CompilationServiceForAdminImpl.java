package ru.practicum.events.compilation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.dto.NewCompilationDto;
import ru.practicum.events.compilation.dto.UpdateCompilationRequest;
import ru.practicum.events.compilation.storage.CompilationStorage;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CompilationServiceForAdminImpl implements CompilationServiceForAdmin {
    private final CompilationStorage compilationStorage;

    @Autowired
    public CompilationServiceForAdminImpl(CompilationStorage compilationStorage) {
        this.compilationStorage = compilationStorage;
    }

    @Override
    public CompilationDto addCompilation(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void deleteCompilationById(Long compId) {

    }

    @Override
    public CompilationDto updateCompilationById(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }
}
