package ru.practicum.events.event.mapper;

import ru.practicum.events.event.dto.LocationDto;
import ru.practicum.events.event.model.location.Location;

public class LocationMapper {
    public static Location locationDtoToLocation(LocationDto locationDto) {
        return Location.builder()
                .lat(locationDto.getLat())
                .lon(locationDto.getLon())
                .build();
    }

    public static LocationDto locationToLocationDto(Location location) {
        return LocationDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}
