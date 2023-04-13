package ru.practicum.statsserver.stats.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.statsserver.hit.controller.HitController;
import ru.practicum.statsserver.hit.model.Hit;
import ru.practicum.statsserver.hit.storage.HitRepository;
import ru.practicum.statsserver.stats.service.StatsServiceImpl;
import ru.practicum.statsserver.util.DateFormatter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(StatsController.class)
@AutoConfigureMockMvc
class StatsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    StatsServiceImpl statsService;

    @Autowired
    private HitRepository hitRepository;
    private Hit hit1;
    private Hit hit2;
    private Hit hit3;
    private Hit hit4;
    private Hit hit5;
    private List<String> uris = Arrays.asList("/events/1");
    private boolean unique = false;
    private final LocalDateTime start = DateFormatter.formatDate("2019-09-06 11:00:00");
    private final LocalDateTime end = DateFormatter.formatDate("2030-09-06 11:00:00");
    private final LocalDateTime newStartFuture = DateFormatter.formatDate("2045-09-06 11:00:00");
    private final LocalDateTime newEndFuture = DateFormatter.formatDate("2045-09-06 11:00:00");
    private final LocalDateTime newStartPast = DateFormatter.formatDate("1987-09-06 11:00:00");
    private final LocalDateTime newEndPast = DateFormatter.formatDate("1987-09-06 11:00:00");

    @BeforeEach
    private void init() {
        hit1 = hitRepository.save(new Hit(null, "ewm-main-service", "/events/1", "192.163.0.1", DateFormatter.formatDate("2022-09-06 11:00:00")));
        hit2 = hitRepository.save(new Hit(null, "ewm-main-service", "/events/1", "192.163.0.1", DateFormatter.formatDate("2022-09-07 12:00:00")));
        hit3 = hitRepository.save(new Hit(null, "ewm-main-service", "/events/1", "192.163.0.1", DateFormatter.formatDate("2022-09-08 13:00:00")));
        hit4 = hitRepository.save(new Hit(null, "ewm-main-service", "/events/1", "192.163.100.100", DateFormatter.formatDate("2023-03-03 07:00:00")));
        hit5 = hitRepository.save(new Hit(null, "ewm-main-service", "/events/56", "192.163.120.120", DateFormatter.formatDate("2023-03-06 09:00:00")));
    }
    @Test
    void getStatsTest() {

    }
}