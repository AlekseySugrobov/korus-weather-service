package ru.sugrobov.weather.api.weather;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sugrobov.weather.model.history.HistoryResponse;
import ru.sugrobov.weather.model.response.OperationResponse;
import ru.sugrobov.weather.model.user.Role;
import ru.sugrobov.weather.model.user.User;
import ru.sugrobov.weather.repository.HistoryRepository;
import ru.sugrobov.weather.service.IUserService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"History"})
@Slf4j
public class HistoryController {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Получение истории операций")
    @RequestMapping(value = "/getHistory", method = RequestMethod.GET)
    public HistoryResponse getHistory(){
        User user = userService.getUser();
        HistoryResponse historyResponse = new HistoryResponse();
        if(user == null){
            historyResponse.setOperationMessage("forbidden");
            historyResponse.setOperationState(OperationResponse.ResponseStatusEnum.NO_ACCESS);
        } else {
            historyResponse.setOperationState(OperationResponse.ResponseStatusEnum.SUCCESS);
            if(user.getRole().equals(Role.ADMIN)){
                historyResponse.setOperationMessage("loaded all history list");
                historyResponse.setHistory(historyRepository.findAll());
            } else {
                historyResponse.setOperationMessage("load history list for user " + user.getUserId());
                historyResponse.setHistory(historyRepository.findByUserOrderByActionDateDesc(user));
            }
        }
        return historyResponse;
    }
}
