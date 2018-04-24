package ru.sugrobov.weather.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sugrobov.weather.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

  User findOneByEmail(String email);

  User findOneByEmailAndPassword(String email, String password);
}
