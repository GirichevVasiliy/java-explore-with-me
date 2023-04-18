package ru.practicum.events.event.model;

import ru.practicum.category.model.Category;
import ru.practicum.events.event.model.location.Location;
import ru.practicum.users.model.User;

import java.time.LocalDateTime;

public class Event {
    String annotation; // example: Эксклюзивность нашего шоу гарантирует привлечение максимальной зрительской аудитории Краткое описание
    Category category;
    Long confirmedRequests; // Количество одобренных заявок на участие в данном событии
    LocalDateTime createdOn; // Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")
    String description; //Полное описание события
    LocalDateTime eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")
    Long id;
    User initiator; //Пользователь (краткая информация)
    Location location; //Широта и долгота места проведения события
    boolean paid; // Нужно ли оплачивать участие
    int participantLimit; // Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
    LocalDateTime publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")
    boolean requestModeration; // Нужна ли пре-модерация заявок на участие
    String state; // example: PUBLISHED, Список состояний жизненного цикла события
    String title; // example: Знаменитое шоу 'Летающая кукуруза' Заголовок
    Long views; // Количество просмотрев события
}
