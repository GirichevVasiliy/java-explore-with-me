package ru.practicum.events.compilation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.mapper.CompilationMapper;
import ru.practicum.events.compilation.service.CompilationServicePublic;
import ru.practicum.events.compilation.storage.CompilationStorage;
import ru.practicum.util.FindObjectInRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CompilationServicePublicImpl implements CompilationServicePublic {
    private final CompilationStorage compilationStorage;
    private final FindObjectInRepository findObjectInRepository;

    @Autowired
    public CompilationServicePublicImpl(CompilationStorage compilationStorage,
                                        FindObjectInRepository findObjectInRepository) {
        this.compilationStorage = compilationStorage;
        this.findObjectInRepository = findObjectInRepository;
    }

    @Override
    public List<CompilationDto> getAllCompilations(boolean pinned, int from, int size) {
        Sort sortByPinned = Sort.by(Sort.Direction.ASC, "pinned"); //??????????????????????????????
        Pageable pageable = PageRequest.of(from, size, sortByPinned);
        return compilationStorage.findAll(pageable).stream().map(CompilationMapper::compilationToCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        return CompilationMapper.compilationToCompilationDto(findObjectInRepository.getCompilationById(compId));
    }
}
