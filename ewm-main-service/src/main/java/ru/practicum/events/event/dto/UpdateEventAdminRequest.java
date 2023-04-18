package ru.practicum.events.event.dto;

import java.util.List;

public class UpdateEventAdminRequest { // Данные для изменения информации о событии. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    private List<Long> events; // Список id событий подборки для полной замены текущего списка
}
