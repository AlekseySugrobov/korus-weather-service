package ru.sugrobov.weather.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sugrobov.weather.model.User;
import ru.sugrobov.weather.repository.UserRepository;
import ru.sugrobov.weather.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public User getUser(Long id) {
    return userRepository.findOne(id);
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findOneByEmail(email);
  }

  @Override
  public User findUserByEmailAndPassword(String email, String password) {
    return userRepository.findOneByEmailAndPassword(email, password);
  }
}
