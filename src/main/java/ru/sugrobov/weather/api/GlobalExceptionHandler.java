package ru.sugrobov.weather.api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.sugrobov.weather.model.response.OperationResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public OperationResponse handleBaseException(DataIntegrityViolationException e){
        OperationResponse resp = new OperationResponse();
        resp.setOperationState(OperationResponse.ResponseStatusEnum.ERROR);
        resp.setOperationMessage(e.getRootCause().getMessage());
        return resp;
    }

}
