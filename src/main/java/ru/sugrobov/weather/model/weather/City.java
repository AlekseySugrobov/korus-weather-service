package ru.sugrobov.weather.model.weather;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class City {
    private String name;
    private String country;
}
