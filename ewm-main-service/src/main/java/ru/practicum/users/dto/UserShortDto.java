package ru.practicum.users.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserShortDto {
    Long id;
    String name; // example: Фёдоров Матвей
}
