package ru.sugrobov.weather.model.weather.AdditionalInformation;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Precipitation {
    private String main;
    private String description;
}
