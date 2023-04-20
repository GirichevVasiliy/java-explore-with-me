package ru.practicum.events.event.mapper;

import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.events.event.dto.EventFullDto;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.model.Event;
import ru.practicum.users.mapper.UserMapper;

import java.time.LocalDateTime;

public class EventMapper {
    public static Event eventShortDtoToEvent(EventShortDto eventShortDto){
        return Event.builder()
                .annotation(eventShortDto.getAnnotation())
                .category(CategoryMapper.categoryDtoToCategory(eventShortDto.getCategory()))
                .confirmedRequests(eventShortDto.getConfirmedRequests())
                .eventDate(LocalDateTime.parse(eventShortDto.getEventDate()))
                .id(eventShortDto.getId())
                .initiator(UserMapper.userShortDtoToUser(eventShortDto.getInitiator()))
                .paid(eventShortDto.isPaid())
                .title(eventShortDto.getTitle())
                .views(eventShortDto.getViews())
                .build();
    }
    public static EventShortDto eventToeventShortDto(Event event){
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.categoryToCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(String.valueOf(event.getEventDate()))
                .id(event.getId())
                .initiator(UserMapper.userToUserShortDto(event.getInitiator()))
                .paid(event.isPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }
    public static EventFullDto eventToEventFullDto(Event event){
        return EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.categoryToCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(String.valueOf(event.getCreatedOn()))
                .description(event.getDescription())
                .eventDate(String.valueOf(event.getEventDate()))
                .id(event.getId())
                .initiator(UserMapper.userToUserShortDto(event.getInitiator()))
                .location(LocationMapper.locationToLocationDto(event.getLocation()))
                .paid(event.isPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(String.valueOf(event.getPublishedOn()))
                .requestModeration(event.isRequestModeration())
                .state(event.getState().name())
                .views(event.getViews())
                .build();
    }

}
