package ru.practicum.events.request.mapper;


import ru.practicum.events.request.dto.ParticipationRequestDto;
import ru.practicum.events.request.model.Request;

public class RequestMapper {
    public static ParticipationRequestDto requestToParticipationRequestDto(Request request){
        return ParticipationRequestDto.builder()
                .created(request.getCreated())
                .event(request.getEvent().getId())
                .id(request.getId())
                .requester(request.getRequester().getId())
                .status(request.getStatus().name())
                .build();
    }
}
