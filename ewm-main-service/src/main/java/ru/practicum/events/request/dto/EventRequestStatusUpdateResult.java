package ru.practicum.events.request.dto;

import lombok.*;
import ru.practicum.events.request.dto.ParticipationRequestDto;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult { // Результат подтверждения/отклонения заявок на участие в событии
    private ParticipationRequestDto confirmedRequests; //
    private ParticipationRequestDto rejectedRequests;
}
