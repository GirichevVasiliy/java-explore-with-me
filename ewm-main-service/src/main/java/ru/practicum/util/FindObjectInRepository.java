package ru.practicum.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.category.model.Category;
import ru.practicum.category.storage.CategoryRepository;
import ru.practicum.events.compilation.model.Compilation;
import ru.practicum.events.compilation.storage.CompilationStorage;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.events.request.model.Request;
import ru.practicum.events.request.model.RequestStatus;
import ru.practicum.events.request.storage.RequestRepository;
import ru.practicum.exception.ResourceNotFoundException;
import ru.practicum.users.model.User;
import ru.practicum.users.storage.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FindObjectInRepository {
    private final CategoryRepository categoryRepository;
    private final CompilationStorage compilationStorage;
    private final EventRepository eventRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public FindObjectInRepository(CategoryRepository categoryRepository, CompilationStorage compilationStorage,
                                  EventRepository eventRepository, RequestRepository requestRepository,
                                  UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.compilationStorage = compilationStorage;
        this.eventRepository = eventRepository;
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Категория c id = " + id + " не найдена"));
    }

    public Compilation getCompilationById(Long id) {
        return compilationStorage.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Подборка событий c id = " + id + " не найдена"));
    }

    public Event getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));

        return event;
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Запрос на участие c id = " + id + " не найден"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Пользователь c id = " + id + " не найден"));
    }

    public boolean isRelatedEvent(Category category) {
        List<Event> findEventByCategory = eventRepository.findEventByCategoryIs(category);
        return !findEventByCategory.isEmpty();
    }

    public List<Event> confirmedRequests(List<Event> events) {
        Map<Event, Long> countRequest = requestRepository.countRequestByEventInAndStatus(events, RequestStatus.CONFIRMED);
        List<Event> newEvents = new ArrayList<>();
        for (Event e : events) {
            long count = countRequest.getOrDefault(e, 0L);
            e.setConfirmedRequests(count);
            newEvents.add(e);
        }
        return newEvents;
    }

    public long confirmedRequestsForOneEvent(Event event, RequestStatus status) {
        return requestRepository.countRequestByEventAndStatus(event, status);
    }
}
