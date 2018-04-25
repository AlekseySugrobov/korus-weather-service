package ru.sugrobov.weather.api.weather;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.sugrobov.weather.identity.TokenUser;
import ru.sugrobov.weather.model.history.UserAction;
import ru.sugrobov.weather.model.response.OperationResponse;
import ru.sugrobov.weather.model.user.User;
import ru.sugrobov.weather.model.weather.Coordinates;
import ru.sugrobov.weather.model.weather.Weather;
import ru.sugrobov.weather.model.weather.WeatherResponse;
import ru.sugrobov.weather.service.IHistoryService;
import ru.sugrobov.weather.service.IUserService;
import ru.sugrobov.weather.service.IWeatherService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Weather"})
@Slf4j
public class WeatherController {
    @Autowired
    @Qualifier("openWeatherService")
    private IWeatherService weatherService;

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Получение погоды по наименованию города")
    @RequestMapping(value = "/weatherByCity", method = RequestMethod.GET)
    public WeatherResponse getWeatherByCity(@RequestParam String city){
        log.info("searching city by name: {}", city);
        logWeatherRequest(userService.getUser(), "Запрос погоды по городу: " + city);
        Weather weather = weatherService.getWeatherByCity(city);
        return getWeatherResponse(weather);
    }

    @ApiOperation(value = "Получение погоды по координатам")
    @RequestMapping(value = "/weatherByCoordinates", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WeatherResponse getWeatherByCoordinates(@RequestBody Coordinates coordinates){
        log.info("searching weather by coordinates: lat {}, lng {}", coordinates.getLattitude(), coordinates.getLongitude());
        logWeatherRequest(userService.getUser(), String.format("Запрос погоды по координатам: lat %s, lng %s", coordinates.getLattitude(), coordinates.getLattitude()));
        Weather weather = weatherService.getWeatherByCoordinates(coordinates);
        return getWeatherResponse(weather);
    }

    private WeatherResponse getWeatherResponse(Weather weather){
        WeatherResponse weatherResponse = new WeatherResponse();
        if(weather != null){
            weatherResponse.setWeather(weather);
            weatherResponse.setOperationMessage("successfully find weather info");
            weatherResponse.setOperationState(OperationResponse.ResponseStatusEnum.SUCCESS);
        } else {
            weatherResponse.setWeather(new Weather());
            weatherResponse.setOperationMessage("can't find weather info");
            weatherResponse.setOperationState(OperationResponse.ResponseStatusEnum.ERROR);
        }
        return weatherResponse;
    }

    private void logWeatherRequest(User user, String description){
        historyService.writeAction(user, UserAction.WEATHER_REQUEST, description);
    }

}
