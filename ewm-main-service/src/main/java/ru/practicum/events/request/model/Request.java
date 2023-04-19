package ru.practicum.events.request.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class Request {
    Long id; //Идентификатор заявки
    LocalDateTime created; // 2022-09-06T21:10:05.432 Дата и время создания заявки
    Long event; // Идентификатор события
    Long requester; // Идентификатор пользователя, отправившего заявку
    String status; // example: PENDING Статус заявки
}
