package ru.sugrobov.weather.model.weather;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Coordinates {
    @ApiModelProperty(example = "weather", required = true)
    private String longitude;
    @ApiModelProperty(example = "weather", required = true)
    private String lattitude;
}
