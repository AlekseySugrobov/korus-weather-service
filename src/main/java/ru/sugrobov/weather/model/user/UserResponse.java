package ru.sugrobov.weather.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sugrobov.weather.model.response.OperationResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends OperationResponse {
    private User data = new User();
}
