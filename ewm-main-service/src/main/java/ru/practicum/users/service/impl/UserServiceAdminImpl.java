package ru.practicum.users.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ConflictNameAndEmailException;
import ru.practicum.exception.ResourceNotFoundException;
import ru.practicum.users.dto.NewUserRequest;
import ru.practicum.users.dto.UserDto;
import ru.practicum.users.mapper.UserMapper;
import ru.practicum.users.model.User;
import ru.practicum.users.service.UserServiceAdmin;
import ru.practicum.users.storage.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceAdminImpl implements UserServiceAdmin {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceAdminImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsersByIds(List<Long> ids, int from, int size) {
        log.info("Получен запрос на получение всех пользователей по id");
        Pageable pageable = PageRequest.of(from, size);
        List<User> users = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            users = userRepository.findAllUsersByIds(ids, pageable);
        } else {
            users = userRepository.findAllBy(pageable);
        }
        return users.stream().map(UserMapper::userToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(NewUserRequest newUserRequest) {
        log.info("Получен запрос на добавление пользователя {}", newUserRequest);
        User user = UserMapper.newUserRequestToUser(newUserRequest);
        try {
            return UserMapper.userToDto(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new ConflictNameAndEmailException("Почта " + newUserRequest.getEmail() + " или имя пользователя " +
                    newUserRequest.getName() + " уже используется");
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Запрос на добавление пользователя" + newUserRequest + " составлен не корректно ");
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        log.info("Получен запрос на удаления пользователя  по id= {}", userId);
        User user = getUserById(userId);
        userRepository.delete(user);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Пользователь c id = " + id + " не найден"));
    }
}
