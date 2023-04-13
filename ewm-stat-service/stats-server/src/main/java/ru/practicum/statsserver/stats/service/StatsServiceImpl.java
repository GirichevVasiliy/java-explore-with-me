package ru.practicum.statsserver.stats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.StatsDto;
import ru.practicum.statsserver.exception.ValidationDateException;
import ru.practicum.statsserver.stats.storage.StatsRepository;
import ru.practicum.statsserver.util.DateFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    @Autowired
    public StatsServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    public List<StatsDto> getStats(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime newStart = DateFormatter.formatDate(start);
        LocalDateTime newEnd = DateFormatter.formatDate(end);
        validDate(newStart, newEnd);
        if (uris == null && !unique){
            return statsRepository.findByDate(newStart, newEnd);
        }
        if (uris == null && unique){
            return statsRepository.findByDateAndUniqueIp(newStart, newEnd);
        }
        if (!uris.isEmpty() && !unique){
            return statsRepository.findByDateAndUris(newStart, newEnd, uris);
        }
        if (!uris.isEmpty() && unique){
            return statsRepository.findByDateAndUrisWithUniqueIp(newStart, newEnd, uris);
        }
        return new ArrayList<>();
    }
    private void validDate(LocalDateTime start, LocalDateTime end){
        if (end.isBefore(start) || start.isAfter(end)){
            throw new ValidationDateException("Невенно заданы даты для поиска");
        }
    }
}
