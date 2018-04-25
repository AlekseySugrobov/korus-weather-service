package ru.sugrobov.weather.model.session;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sugrobov.weather.model.response.OperationResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class SessionResponse extends OperationResponse {
    @ApiModelProperty(required = true, value = "")
    private SessionItem item;

    public SessionResponse(){}

    public SessionResponse(ResponseStatusEnum state, String message, SessionItem item){
        super.setOperationState(state);
        super.setOperationMessage(message);
        this.setItem(item);
    }
}
