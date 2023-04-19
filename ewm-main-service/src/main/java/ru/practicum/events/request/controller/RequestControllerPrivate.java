package ru.practicum.events.request.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.request.dto.ParticipationRequestDto;
import ru.practicum.events.request.service.RequestServicePrivate;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@Slf4j
public class RequestControllerPrivate {
    private final RequestServicePrivate requestServicePrivate;

    @Autowired
    public RequestControllerPrivate(RequestServicePrivate requestServicePrivate) {
        this.requestServicePrivate = requestServicePrivate;
    }

    /**
     * Информация о заявках текущего пользователя на участие в чужих событиях
     */
    @GetMapping
    List<ParticipationRequestDto> getAllRequestsUserById(@NotNull @PathVariable Long userId) {
        return requestServicePrivate.getAllRequestsUserById(userId);
    }

    /**
     * Добавление запроса от текущего пользователя на участие в событии
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ParticipationRequestDto addRequestEventById(@NotNull @PathVariable Long userId,
                                                @NotNull @RequestParam Long eventId) {
        return requestServicePrivate.addRequestEventById(userId, eventId);
    }

    /**
     * Отмена своего запроса на участие в событии
     */
    @PatchMapping("/{requestId}/cancel")
    ParticipationRequestDto updateRequestById(@NotNull @PathVariable Long userId,
                                              @NotNull @PathVariable Long requestId) {
        return requestServicePrivate.updateRequestById(userId, requestId);
    }
}
