package ru.practicum.statsserver.stats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.StatsDto;
import ru.practicum.statsserver.hit.model.Hit;
import ru.practicum.statsserver.hit.storage.HitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final HitRepository hitRepository;

    @Autowired
    public StatsServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<Hit> hitList = new
        if (unique){
            List<Hit> hitList = hitRepository.getAllHits(start, end, uris);
        } else{

        }
    }
}
