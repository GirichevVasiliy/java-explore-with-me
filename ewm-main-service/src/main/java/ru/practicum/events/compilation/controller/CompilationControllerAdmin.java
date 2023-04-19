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

    @PostMapping
    public CompilationDto addCompilation(@Validated @RequestBody NewCompilationDto newCompilationDto) {
        return compilationServiceForAdmin.addCompilation(newCompilationDto);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCategory(@PathVariable Long compId,
                                         @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return compilationServiceForAdmin.updateCompilationById(compId, updateCompilationRequest);
    }

    @DeleteMapping("/{compId}")
    public void deleteCategoryById(@PathVariable Long compId) {
        compilationServiceForAdmin.deleteCompilationById(compId);
    }
}
