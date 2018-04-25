package ru.sugrobov.weather.model.weather;

import lombok.ToString;
import ru.sugrobov.weather.model.weather.AdditionalInformation.Data;

@lombok.Data
@ToString
public class Weather {
    private Data data;
    private City city;
}
