package ru.practicum.events.request.model;

import lombok.*;
import ru.practicum.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests", schema = "public")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Идентификатор заявки
    @Column(name = "created")
    private LocalDateTime created; // 2022-09-06T21:10:05.432 Дата и время создания заявки
    @Column(name = "event")
    private Long event; // Идентификатор события
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester; // Идентификатор пользователя, отправившего заявку
    @Enumerated(EnumType.STRING)
    private RequestStatus status; // example: PENDING Статус заявки
}
