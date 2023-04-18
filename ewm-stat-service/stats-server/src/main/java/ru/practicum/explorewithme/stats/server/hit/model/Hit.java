package ru.practicum.explorewithme.stats.server.hit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hits", schema = "public")
public class Hit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Идентификатор записи
    @Column(name = "app")
    private String app; // Идентификатор сервиса для которого записывается информация ewm-main-service
    @Column(name = "uri")
    private String uri; // URI для которого был осуществлен запрос  /events/1
    @Column(name = "ip")
    private String ip; //IP-адрес пользователя, осуществившего запрос
    @Column(name = "time_stamp")
    private LocalDateTime timestamp; // Дата и время, когда был совершен запрос к эндпоинту (в формате "yyyy-MM-dd HH:mm:ss")
}
