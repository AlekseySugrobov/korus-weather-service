package ru.sugrobov.weather.util.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sugrobov.weather.model.weather.Weather;
import ru.sugrobov.weather.util.IWeatherObjectMapper;

@Service
public class OpenWeatherObjectMapper extends ObjectMapper implements IWeatherObjectMapper {
    public OpenWeatherObjectMapper(){
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Weather.class, new OpenWeatherDeserializer());
        this.registerModule(module);
    }
}
