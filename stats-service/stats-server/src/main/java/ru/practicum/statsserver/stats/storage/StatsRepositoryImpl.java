package ru.practicum.statsserver.stats.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.statsserver.hit.model.Hit;
import ru.practicum.statsserver.hit.storage.HitRepository;
import ru.practicum.statsserver.stats.model.Stats;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class StatsRepositoryImpl implements StatsRepository{
    @Autowired
    private final HitRepository hitRepository;

    public StatsRepositoryImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    @Override
    public List<Stats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (unique){
        List<Hit> hitList = hitRepository.getAllHits(start, end, uris);
        } else{

        }
        return null;
    }
}
