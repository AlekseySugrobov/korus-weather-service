package ru.sugrobov.weather.util.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import ru.sugrobov.weather.model.weather.AdditionalInformation.Data;
import ru.sugrobov.weather.model.weather.AdditionalInformation.DataMain;
import ru.sugrobov.weather.model.weather.AdditionalInformation.Precipitation;
import ru.sugrobov.weather.model.weather.City;
import ru.sugrobov.weather.model.weather.Weather;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class OpenWeatherDeserializer extends JsonDeserializer<Weather> {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Weather deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Weather weather = new Weather();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode dataNode = node.get("list").get(0);
        JsonNode main = dataNode.get("main");
        weather.setData(getData(dataNode, main));
        weather.setCity(getCity(node));
        log.info(weather.toString());
        return weather;
    }

    private Data getData(JsonNode dataNode, JsonNode main) {
        Data data = new Data();
        data.setDataMain(getDataMain(main));
        JsonNode dataWeather = dataNode.get("weather").get(0);
        data.setPrecipitation(getPrecipitation(dataWeather));
        data.setDate(LocalDateTime.parse(dataNode.get("dt_txt").asText(), formatter));
        return data;
    }

    private Precipitation getPrecipitation(JsonNode dataWeather) {
        Precipitation precipitation = new Precipitation();
        precipitation.setMain(dataWeather.get("main").asText());
        precipitation.setDescription(dataWeather.get("description").asText());
        return precipitation;
    }

    private DataMain getDataMain(JsonNode main) {
        DataMain dataMain = new DataMain();
        dataMain.setTemp(main.get("temp").asText());
        dataMain.setPressure(main.get("pressure").asText());
        dataMain.setHumidity(main.get("humidity").asText());
        return dataMain;
    }

    private City getCity(JsonNode node) {
        City city = new City();
        JsonNode dataCity = node.get("city");
        if (Objects.nonNull(dataCity.get("country"))) {
            city.setCountry(dataCity.get("country").asText());
        }
        if (Objects.nonNull(dataCity.get("name"))) {
            city.setName(dataCity.get("name").asText());
        }
        return city;
    }
}
