package ru.practicum.events.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Value
public class NewEventDto {
    @NotNull(message = "Поле annotation должно быть заполнено")
    @Size(min = 20, max = 200, message = "Минимальное кол-во символов для описания: 20. Максимальное: 200")
    String annotation; //Краткое описание события
    @NotNull(message = "Поле id должно быть заполнено")
    Long id; // id категории к которой относится событие
    @NotNull(message = "Поле description должно быть заполнено")
    @Size(min = 20, max = 7000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 7000")
    String description; //Полное описание события

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @NotBlank(message = "Поле location должно быть заполнено")
    LocationDto location; //Широта и долгота места проведения события
    @org.springframework.beans.factory.annotation.Value("false")
    Boolean paid; // Нужно ли оплачивать участие
    @org.springframework.beans.factory.annotation.Value("0")
    Integer participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    @org.springframework.beans.factory.annotation.Value("true")
    Boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    @NotNull
    @NotBlank(message = "Поле title должно быть заполнено")
    String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
}
