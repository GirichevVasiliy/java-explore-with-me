package ru.practicum.events.compilation.mapper;

import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.dto.NewCompilationDto;
import ru.practicum.events.compilation.model.Compilation;
import ru.practicum.events.event.mapper.EventMapper;
import ru.practicum.events.event.model.Event;

import java.util.Set;
import java.util.stream.Collectors;

public class CompilationMapper {
    public static Compilation compilationDtoToCompilation(CompilationDto compilationDto, Set<Event> events) {
        return Compilation.builder()
                .id(compilationDto.getId())
                .events(events)
                .pinned(compilationDto.isPinned())
                .title(compilationDto.getTitle())
                .build();
    }
/*    public static Compilation newCompilationDtoToCompilation(NewCompilationDto newCompilationDto) {
        return Compilation.builder()
                .events(newCompilationDto.getEvents().stream().map(EventMapper::eventShortDtoToEvent).collect(Collectors.toSet()))
                .pinned(newCompilationDto.isPinned())
                .title(newCompilationDto.getTitle())
                .build();
    }*/
    public static Compilation newCompilationDtoToCompilationAndEvents(NewCompilationDto newCompilationDto, Set<Event> events) {
        return Compilation.builder()
                .events(events)
                .pinned(newCompilationDto.getPinned() == null ? false : newCompilationDto.getPinned())
                .title(newCompilationDto.getTitle())
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
