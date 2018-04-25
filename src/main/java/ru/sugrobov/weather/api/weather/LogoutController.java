package ru.sugrobov.weather.api.weather;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sugrobov.weather.model.history.UserAction;
import ru.sugrobov.weather.model.user.User;
import ru.sugrobov.weather.service.IHistoryService;
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"logout"})
@Slf4j
public class LogoutController {

    @Autowired
    private IHistoryService historyService;

    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void logout(@RequestBody User user){
        historyService.writeAction(user, UserAction.LOGOUT, "Выход");
    }

}
