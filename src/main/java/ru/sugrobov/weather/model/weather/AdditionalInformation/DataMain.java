package ru.sugrobov.weather.model.weather.AdditionalInformation;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataMain {
    private String temp;
    private String pressure;
    private String humidity;
}
