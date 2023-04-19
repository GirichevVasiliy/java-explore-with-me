package ru.practicum.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.users.dto.UserDto;
import ru.practicum.users.dto.UserShortDto;
import ru.practicum.users.service.UserServiceAdmin;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@Slf4j
public class UserControllerAdmin {
    private final UserServiceAdmin userServiceAdmin;

    @Autowired
    public UserControllerAdmin(UserServiceAdmin userServiceAdmin) {
        this.userServiceAdmin = userServiceAdmin;
    }

    @GetMapping
    List<UserDto> getAllUsersByIds(@RequestParam List<Long> ids,
                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                   @Positive @Min(1) @RequestParam(defaultValue = "10") Integer size) {
        return userServiceAdmin.getAllUsersByIds(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto addUser(@Validated @RequestBody UserShortDto userShortDto) {
        return userServiceAdmin.addUser(userShortDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@NotNull @PathVariable Long userId) {
        userServiceAdmin.deleteUserById(userId);
    }
}
