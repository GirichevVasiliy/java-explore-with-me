package ru.practicum.users.service;

import ru.practicum.users.dto.UserDto;
import ru.practicum.users.dto.UserShortDto;

import java.util.List;

public interface UserServiceAdmin {
    List<UserDto> getAllUsersByIds(List<Long> ids, int from, int size);
    UserDto addUser(UserShortDto userShortDto);
    void deleteUserById(Long userId);
}
