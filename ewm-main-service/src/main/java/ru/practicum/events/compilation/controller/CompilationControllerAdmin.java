package ru.practicum.events.compilation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.compilation.dto.CompilationDto;
import ru.practicum.events.compilation.dto.NewCompilationDto;
import ru.practicum.events.compilation.dto.UpdateCompilationRequest;
import ru.practicum.events.compilation.service.CompilationServiceAdmin;

@RestController
@RequestMapping(path = "/admin/compilations")
@Slf4j
public class CompilationControllerAdmin {
    private final CompilationServiceAdmin compilationServiceForAdmin;

    @Autowired
    public CompilationControllerAdmin(CompilationServiceAdmin compilationServiceForAdmin) {
        this.compilationServiceForAdmin = compilationServiceForAdmin;
    }

    /**
     * Добавление новой подборки (подборка может не содержать событий)
     */
    @PostMapping
    public CompilationDto addCompilation(@Validated @RequestBody NewCompilationDto newCompilationDto) {
        return compilationServiceForAdmin.addCompilation(newCompilationDto);
    }
    /**
     * обновить информацию о подборке
     */
    @PatchMapping("/{compId}")
    public CompilationDto updateCompilationById(@PathVariable Long compId,
                                         @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return compilationServiceForAdmin.updateCompilationById(compId, updateCompilationRequest);
    }

    /**
     * Удаление подборки
     */
    @DeleteMapping("/{compId}")
    public void deleteCompilationById(@PathVariable Long compId) {
        compilationServiceForAdmin.deleteCompilationById(compId);
    }
}
