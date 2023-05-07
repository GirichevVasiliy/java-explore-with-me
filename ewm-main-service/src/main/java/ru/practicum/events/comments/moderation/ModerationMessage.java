package ru.practicum.events.comments.moderation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.practicum.exception.BadRequestException;
import ru.practicum.exception.ResourceNotFoundException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ModerationMessage {
    @Value("${moderation.words.file.path}")
    private String wordsFilePath;

    public void moderationMessage(String text) {
        try {
            String url = "src/main/java/ru/practicum/events/comments/moderation/words.txt";
            Stream<String> lines = Files.lines((Paths.get(url)), Charset.defaultCharset());
            List<String> forbiddenWords = lines.filter(s -> !s.isEmpty()).collect(Collectors.toList());
            List<String> newText = Stream.of(text.split(" ")).map(String::toLowerCase).collect(Collectors.toList());
            newText.retainAll(forbiddenWords);
            if (!newText.isEmpty()) {
                throw new BadRequestException("Комментарий является недопустимым и отклонен");
            }
        } catch (IOException e) {
            throw new ResourceNotFoundException("Ошибка чтения библиотеки для модерации контента");
        }
    }
}
