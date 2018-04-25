package ru.sugrobov.weather.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Login {
    @ApiModelProperty(example = "weather", required = true)
    private String userName = "";
    @ApiModelProperty(example = "weather", required = true)
    private String password = "";
}
