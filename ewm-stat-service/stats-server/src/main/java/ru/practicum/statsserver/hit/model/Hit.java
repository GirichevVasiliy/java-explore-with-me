package ru.practicum.statsserver.hit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit hit = (Hit) o;
        return id == hit.id && Objects.equals(app, hit.app) && Objects.equals(uri, hit.uri) && Objects.equals(ip, hit.ip) && Objects.equals(timestamp, hit.timestamp);
    }

    @Override
    public int hashCode() {
        int hash = 17 + (id == null ? 0 : id.hashCode());
        if (app != null) {
            hash = hash + app.hashCode();
        }
        hash = hash * 31;
        if (uri != null) {
            hash = hash + uri.hashCode();
        }
        hash = hash * 33;
        if (ip != null) {
            hash = hash + ip.hashCode();
        }
        return hash;
    }
}
