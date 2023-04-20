package ru.practicum.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.model.Category;
import ru.practicum.category.storage.CategoryRepository;
import ru.practicum.events.compilation.model.Compilation;
import ru.practicum.events.compilation.storage.CompilationStorage;
import ru.practicum.events.event.dto.EventShortDto;
import ru.practicum.events.event.model.Event;
import ru.practicum.events.event.storage.EventRepository;
import ru.practicum.events.request.model.Request;
import ru.practicum.events.request.storage.RequestRepository;
import ru.practicum.exception.ResourceNotFoundException;
import ru.practicum.users.model.User;
import ru.practicum.users.storage.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Категория c id = " + id + " не найдена"));
    }
    public Compilation getCompilationById(Long id){
        return compilationStorage.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Подборка событий c id = " + id + " не найдена"));
    }
    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Событие c id = " + id + " не найдено"));
    }
    public Request getRequestById(Long id){
        return requestRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Запрос на участие c id = " + id + " не найден"));
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Пользователь c id = " + id + " не найден"));
    }
    public boolean isRelatedEvent(Category category){
        Optional<Event> findEventByCategory = eventRepository.findEventByCategoryIs(category);
        return findEventByCategory.isPresent();
    }
    public boolean isSearchForUniqueCategory(String name){
        Optional<Category> findCategories = categoryRepository.findCategoriesByName(name);
        return findCategories.isPresent();
    }
    public Set<Event> getAllEventsByIds(List<Long> eventsIds){
        return eventRepository.findAllByIdIsIn(eventsIds);
    }
}