package ru.practicum.users.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.users.dto.UserDto;
import ru.practicum.users.dto.UserShortDto;
import ru.practicum.users.service.UserServiceAdmin;
import ru.practicum.users.storage.UserRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserServiceAdminImpl implements UserServiceAdmin {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceAdminImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsersByIds(List<Long> ids, int from, int size) {
        return null;
    }

    @Transactional
    @Override
    public UserDto addUser(UserShortDto userShortDto) {
        return null;
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {

    }
}
