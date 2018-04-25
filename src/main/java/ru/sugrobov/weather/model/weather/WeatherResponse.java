package ru.sugrobov.weather.model.weather;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sugrobov.weather.model.response.OperationResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class WeatherResponse extends OperationResponse {
    @ApiModelProperty(required = true, value = "")
    private Weather weather;
}
