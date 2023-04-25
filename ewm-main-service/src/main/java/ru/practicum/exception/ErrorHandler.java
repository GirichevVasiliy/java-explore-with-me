package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.exception.model.ApiError;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiError handleResourceNotFoundException(final ResourceNotFoundException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("The required object was not found")
                .status("NOT_FOUND")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("Incorrectly made request.")
                .status("BAD_REQUEST")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ApiError handleConflictDeleteException(final ConflictDeleteException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("For the requested operation the conditions are not met.")
                .status("CONFLICT")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ApiError handleConflictRequestException(final ConflictRequestException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("For the requested operation the conditions are not met.")
                .status("CONFLICT")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ApiError handleConflictNameCategoryException(final ConflictNameCategoryException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("Integrity constraint has been violated.")
                .status("CONFLICT")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ApiError handleConflictNameAndEmailException(final ConflictNameAndEmailException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("Integrity constraint has been violated.")
                .status("CONFLICT")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenEventException(final ForbiddenEventException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("For the requested operation the conditions are not met.")
                .status("FORBIDDEN")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleValidationDateException(final ValidationDateException e) {
        ApiError apiError = ApiError.builder()
                .errors(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .message(e.getMessage())
                .reason("Incorrectly made request.")
                .status("BAD_REQUEST")
                .timestamp(LocalDateTime.now())
                .build();
        return apiError;
    }
}
