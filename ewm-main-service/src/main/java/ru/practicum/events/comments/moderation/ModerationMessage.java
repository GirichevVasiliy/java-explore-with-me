package ru.practicum.events.comments.moderation;

import org.springframework.stereotype.Service;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ResourceNotFoundException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ModerationMessage {
    public void moderationMessage(String text) {
        try (Stream<String> lines = Files.lines(Paths.get(getClass().getClassLoader().getResource("words.txt").toURI()), Charset.defaultCharset())) {
            List<String> newText = Stream.of(text.split(" ")).map(String::toLowerCase).collect(Collectors.toList());
            newText.retainAll(lines.collect(Collectors.toList()));
            if (!newText.isEmpty()) {
                throw new BadRequestException("Комментарий является недопустимым и отклонен");
            }
        } catch (IOException | URISyntaxException e) {
            throw new ResourceNotFoundException("Ошибка чтения библиотеки для модерации контента");
        }
    }
}