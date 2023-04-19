package ru.practicum.events.event.model.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {
    float lat; // example: 55.754167 - Широта
    float lon; // example: 37.62 - Долгота
}
