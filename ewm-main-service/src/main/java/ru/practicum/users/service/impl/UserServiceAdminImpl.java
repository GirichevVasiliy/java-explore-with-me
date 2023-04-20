package ru.practicum.users.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exception.ConflictNameAndEmailException;
import ru.practicum.users.dto.NewUserRequest;
import ru.practicum.users.dto.UserDto;
import ru.practicum.users.mapper.UserMapper;
import ru.practicum.users.model.User;
import ru.practicum.users.service.UserServiceAdmin;
import ru.practicum.users.storage.UserRepository;
import ru.practicum.util.FindObjectInRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserServiceAdminImpl implements UserServiceAdmin {
    private final UserRepository userRepository;
    private final FindObjectInRepository findObjectInRepository;

    @Autowired
    public UserServiceAdminImpl(UserRepository userRepository,
                                FindObjectInRepository findObjectInRepository) {
        this.userRepository = userRepository;
        this.findObjectInRepository = findObjectInRepository;
    }

    @Override
    public List<UserDto> getAllUsersByIds(List<Long> ids, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        List<User> users = userRepository.findAllUsersByIds(ids, pageable);
        return users.stream().map(UserMapper::userToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto addUser(NewUserRequest newUserRequest) {
        User user = UserMapper.newUserRequestToUser(newUserRequest);
        try {
            return UserMapper.userToDto(userRepository.save(user));
        } catch (Exception e) {
            throw new ConflictNameAndEmailException("Почта " + newUserRequest.getEmail() + " или имя пользователя " +
                    newUserRequest.getName() + " уже используется");
        }
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        User user = findObjectInRepository.getUserById(userId);
        userRepository.delete(user);
    }
}
