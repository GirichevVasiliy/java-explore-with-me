package ru.practicum.events.compilation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.mapper.CompilationMapper;
import ru.practicum.events.compilation.model.Compilation;
import ru.practicum.events.compilation.service.CompilationServicePublic;
import ru.practicum.events.compilation.storage.CompilationStorage;
import ru.practicum.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CompilationServicePublicImpl implements CompilationServicePublic {
    private final CompilationStorage compilationStorage;

    @Autowired
    public CompilationServicePublicImpl(CompilationStorage compilationStorage) {
        this.compilationStorage = compilationStorage;
    }

    @Override
    public List<CompilationDto> getAllCompilations(Boolean pinned, int from, int size) {
        log.info("Получен запрос поиск всех подборок событий, по условию закрепления {}", pinned);
        Pageable pageable = PageRequest.of(from, size);
        return compilationStorage.findAllByPinnedIs(pinned, pageable).stream()
                .map(CompilationMapper::compilationToCompilationDto).collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        log.info("Получен запрос на поиск подборки событий по id= {}", compId);
        return CompilationMapper.compilationToCompilationDto(getCompilationByIdInRepository(compId));
    }

    private Compilation getCompilationByIdInRepository(Long id) {
        return compilationStorage.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Подборка событий c id = " + id + " не найдена"));
    }
}
