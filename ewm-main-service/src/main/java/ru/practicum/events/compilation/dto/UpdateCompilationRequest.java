package ru.practicum.events.compilation.dto;

import java.util.List;

public class UpdateCompilationRequest { //Изменение информации о подборке событий. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    private List<Long> events; //Список id событий подборки для полной замены текущего списка
    private boolean pinned; //Закреплена ли подборка на главной странице сайта
    private String title; // Заголовок подборки
}
