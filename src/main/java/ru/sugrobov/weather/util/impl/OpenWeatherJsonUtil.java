package ru.sugrobov.weather.util.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sugrobov.weather.model.weather.Weather;
import ru.sugrobov.weather.util.IJsonUtil;

import java.io.IOException;

@Service
@Slf4j
public class OpenWeatherJsonUtil implements IJsonUtil {

    @Autowired
    OpenWeatherObjectMapper mapper;

    @Override
    public Weather parseJson(String json) {
        System.setProperty("file.encoding", "UTF-8");
        Weather weather = null;
        log.info("JSON: " + json);
        try {
            weather = mapper.readValue(json, Weather.class);
        } catch (JsonParseException | JsonMappingException e) {
            log.error("Not valid  Json", e);
        } catch (IOException e) {
            log.error("IO exception", e);
        } catch (NullPointerException e){
            log.error("JSON is null", e);
        }
        return weather;
    }
}
