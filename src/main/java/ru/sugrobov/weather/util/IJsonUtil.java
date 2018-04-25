package ru.sugrobov.weather.util;

import ru.sugrobov.weather.model.weather.Weather;

public interface IJsonUtil {
    Weather parseJson(String json);
}
