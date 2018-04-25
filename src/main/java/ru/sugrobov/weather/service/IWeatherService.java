package ru.sugrobov.weather.service;

import ru.sugrobov.weather.model.weather.Coordinates;
import ru.sugrobov.weather.model.weather.Weather;

public interface IWeatherService {
    Weather getWeatherByCity(String city);
    Weather getWeatherByCoordinates(Coordinates coordinates);
}
