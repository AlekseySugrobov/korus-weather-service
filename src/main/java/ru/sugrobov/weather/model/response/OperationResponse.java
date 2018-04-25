package ru.sugrobov.weather.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OperationResponse {
    public enum ResponseStatusEnum{SUCCESS, ERROR, WARINING, NO_ACCESS}
    @ApiModelProperty(required = true)
    private ResponseStatusEnum operationState;
    private String operationMessage;
}
