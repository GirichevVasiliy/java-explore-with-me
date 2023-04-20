package ru.practicum.events.compilation.mapper;

import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.model.Compilation;
import ru.practicum.events.event.mapper.EventMapper;

import java.util.stream.Collectors;

public class CompilationMapper {
    public static Compilation compilationDtoToCompilation(CompilationDto compilationDto) {
        return Compilation.builder()
                .id(compilationDto.getId())
                .events(compilationDto.getEvents().stream().map(EventMapper::eventShortDtoToEvent).collect(Collectors.toSet()))
                .pinned(compilationDto.isPinned())
                .title(compilationDto.getTitle())
                .build();
    }
    public static CompilationDto compilationToCompilationDto(Compilation compilation){
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(compilation.getEvents().stream().map(EventMapper::eventToeventShortDto).collect(Collectors.toList()))
                .pinned(compilation.isPinned())
                .title(compilation.getTitle())
                .build();
    }
}
