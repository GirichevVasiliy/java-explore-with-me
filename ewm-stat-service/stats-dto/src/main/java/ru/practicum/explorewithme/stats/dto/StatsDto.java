package ru.practicum.explorewithme.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@AllArgsConstructor
public class StatsDto {
    @NotBlank(message = "Поле \"app\" должно быть заполнено")
    String app; //ewm-main-service
    @NotBlank(message = "Поле \"uri\" должно быть заполнено")
    String uri; // /events/1
    Long hits; // кол-во
}
