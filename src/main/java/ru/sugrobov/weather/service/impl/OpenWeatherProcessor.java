package ru.sugrobov.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sugrobov.weather.model.weather.Weather;
import ru.sugrobov.weather.service.IWeatherProccessor;
import ru.sugrobov.weather.util.IJsonUtil;

import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class OpenWeatherProcessor implements IWeatherProccessor {

    @Autowired
    @Qualifier("openWeatherJsonUtil")
    private IJsonUtil jsonUtil;

    private ExecutorService service = Executors.newFixedThreadPool(2);

    @Override
    public Weather getWeather(String url) {
        Callable<Weather> callable = () -> {
            Weather weather = null;
            String json = null;

            RestTemplate restTemplate = new RestTemplate();
            try {
                json = restTemplate.getForObject(new URI(url), String.class);
            } catch (Exception e) {
                log.error("Invalid format URI");
            }
            try {
                weather = jsonUtil.parseJson(json);
            } catch (Exception e) {
                log.error("Invalid format json", e);
            }
            return weather;
        };

        Future<Weather> future = service.submit(callable);
        try {
            return future.get();
        } catch (InterruptedException e) {
            log.error("Callable Exception future = " + future, e);
        } catch (Exception e) {
            log.error("Got Exception", e);
        }
        return null;
    }
}
