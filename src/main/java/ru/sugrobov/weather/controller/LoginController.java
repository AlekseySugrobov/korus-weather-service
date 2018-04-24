package ru.sugrobov.weather.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sugrobov.weather.model.User;
import ru.sugrobov.weather.service.IUserService;

@RestController
@RequestMapping("api")
@Slf4j
public class LoginController {

  private final IUserService userService;

  @Autowired
  public LoginController(IUserService userService) {
    this.userService = userService;
  }

  @RequestMapping(
      value = "/login",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> login(@RequestBody User user) {
    log.info("trying to login with email {}", user.getEmail());
    if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())) {
      log.warn("passed empty credentials");
      return new ResponseEntity<>(new User(), HttpStatus.OK);
    }
    if (userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()) == null){
      log.warn("can't find user in database");
      return new ResponseEntity<>(new User(), HttpStatus.OK);
    }
    User foundedUser = userService.findUserByEmail(user.getEmail());
    log.info("user founded in database: [id: {}, firstName: {}, lastName: {}]", foundedUser.getFirstName(), foundedUser.getLastName());
    return new ResponseEntity<>(foundedUser, HttpStatus.OK);
  }
}
