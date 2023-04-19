package ru.practicum.events.compilation.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
@Builder
@Value
public class UpdateCompilationRequest { //Изменение информации о подборке событий. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    List<Long> events; //Список id событий подборки для полной замены текущего списка
    boolean pinned; //Закреплена ли подборка на главной странице сайта
    String title; // Заголовок подборки
}
