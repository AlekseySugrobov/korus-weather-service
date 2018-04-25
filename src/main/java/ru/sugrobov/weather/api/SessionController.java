package ru.sugrobov.weather.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sugrobov.weather.model.response.OperationResponse;
import ru.sugrobov.weather.model.session.SessionItem;
import ru.sugrobov.weather.model.session.SessionResponse;
import ru.sugrobov.weather.model.user.Login;
import ru.sugrobov.weather.model.user.User;
import ru.sugrobov.weather.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags={"Authentication"})
public class SessionController {
    @Autowired
    private UserRepository userRepository;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Will return a security token, which must be passed in every request", response = SessionResponse.class) })
    @RequestMapping(value = "/session", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SessionResponse newSession(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
        User user = userRepository.findOneByUserIdAndPassword(login.getUserName(), login.getPassword()).orElse(null);
        SessionResponse sessionResponse = new SessionResponse();
        SessionItem sessionItem = new SessionItem();
        if(user != null){
            sessionItem.setToken("xxx.xxx.xxx");
            sessionItem.setUserId(user.getUserId());
            sessionItem.setFirstName(user.getFirstName());
            sessionItem.setLastName(user.getLastName());
            sessionItem.setEmail(user.getEmail());
            sessionResponse.setOperationState(OperationResponse.ResponseStatusEnum.SUCCESS);
            sessionResponse.setOperationMessage("Dummy Login Success");
            sessionResponse.setItem(sessionItem);
        } else {
            sessionResponse.setOperationState(OperationResponse.ResponseStatusEnum.ERROR);
            sessionResponse.setOperationMessage("Login failed");
        }
        return sessionResponse;
    }
}
