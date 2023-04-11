package ru.practicum;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
public class HitDto {
    private final Long id;
    @NotBlank(message = "Поле \"app\" должно быть заполнено")
    private final String app;
    @NotBlank(message = "Поле \"uri\" должно быть заполнено")
    private final String uri;
    @NotBlank(message = "Поле \"ip\" должно быть заполнено")
    private final String ip;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timestamp;

}
