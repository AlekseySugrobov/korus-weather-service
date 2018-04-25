package ru.sugrobov.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.sugrobov.weather.model.weather.Coordinates;
import ru.sugrobov.weather.model.weather.Weather;
import ru.sugrobov.weather.service.IWeatherProccessor;
import ru.sugrobov.weather.service.IWeatherService;

@Service
@Slf4j
@PropertySource("classpath:weather.properties")
public class OpenWeatherService implements IWeatherService {
    @Value("${weather.url}")
    private String url;

    @Value("${weather.appId}")
    private String appId;

    @Autowired
    @Qualifier("openWeatherProcessor")
    private IWeatherProccessor weatherProccessor;

    @Override
    public Weather getWeatherByCity(String city) {
        return weatherProccessor.getWeather(String.format("%sq=%s%s", url, city, appId));
    }

    @Override
    public Weather getWeatherByCoordinates(Coordinates coordinates) {
        return weatherProccessor.getWeather(String.format("%slat=%s&lon=%s%s", url, coordinates.getLattitude(), coordinates.getLongitude(), appId));
    }
}
