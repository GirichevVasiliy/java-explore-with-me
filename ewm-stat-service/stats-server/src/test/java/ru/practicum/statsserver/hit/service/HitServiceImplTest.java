package ru.practicum.statsserver.hit.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.practicum.HitDto;
import ru.practicum.statsserver.hit.storage.HitRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class HitServiceImplTest {
    @Autowired
    HitServiceImpl hitService;
    @Autowired
    HitRepository hitRepository;
    private HitDto hitDto1;
    private HitDto hitDto2;
    private HitDto hitDto3;

    @BeforeEach
    private void init() {
        hitDto1 = HitDto.builder()
                .app("ewm-main-service")
                .uri("/events/1")
                .ip("192.163.0.1")
                .timestamp("2022-09-06 11:00:00")
                .build();
        hitDto2 = HitDto.builder()
                .app("ewm-main-service")
                .uri("/events/1")
                .ip("192.163.0.101")
                .timestamp("2022-09-06 13:00:00")
                .build();
        hitDto3 = HitDto.builder()
                .app("ewm-main-service")
                .uri("/events/56")
                .ip("192.163.12.99")
                .timestamp("2022-09-07 14:00:00")
                .build();
    }

    @Test
    void addHitIntegrationTest() {

    }
}