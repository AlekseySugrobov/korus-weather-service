package ru.sugrobov.weather.service;

import java.util.List;
import ru.sugrobov.weather.model.User;

public interface IUserService {
  List<User> getUsers();

  User getUser(Long id);

  User createUser(User user);

  User findUserByEmail(String email);

  User findUserByEmailAndPassword(String email, String password);
}
