package ru.sugrobov.weather.model.weather.AdditionalInformation;

import lombok.ToString;

import java.time.LocalDateTime;

@lombok.Data
@ToString
public class Data {
    private DataMain dataMain;
    private Precipitation precipitation;
    private LocalDateTime date;
}
