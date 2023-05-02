package ru.practicum.users.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    String email;
    Long id;
    String name;
}
