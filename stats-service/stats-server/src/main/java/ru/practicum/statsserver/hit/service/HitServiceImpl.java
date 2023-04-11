package ru.practicum.statsserver.hit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.HitDto;
import ru.practicum.statsserver.hit.mapper.HitMapper;
import ru.practicum.statsserver.hit.model.Hit;
import ru.practicum.statsserver.hit.storage.HitRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
public class HitServiceImpl implements HitService{
    private final HitRepository hitRepository;
    @Autowired
    public HitServiceImpl(@Qualifier("hitRepository")HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }
    @Transactional
    @Override
    public void addHit(HitDto hitDto) {
        log.info("Сохранение запроса к сервису" + hitDto.getUri());
        Hit hit = HitMapper.toHit(hitDto);
        hitRepository.save(hit);
    }
}
