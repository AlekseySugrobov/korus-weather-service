package ru.sugrobov.weather.service;

import ru.sugrobov.weather.model.weather.Weather;

public interface IWeatherProccessor {
    Weather getWeather(String url);
}
