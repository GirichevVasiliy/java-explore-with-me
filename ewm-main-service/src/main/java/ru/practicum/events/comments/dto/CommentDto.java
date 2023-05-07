package ru.practicum.events.comments.dto;

import lombok.Builder;
import lombok.Value;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.users.dto.UserShortDto;

import java.time.LocalDateTime;

@Value
@Builder
public class CommentDto {
    Long id;
    String text;
    LocalDateTime createdOn;
    UserShortDto author;
    EventShortDto event;
    String state;
}
