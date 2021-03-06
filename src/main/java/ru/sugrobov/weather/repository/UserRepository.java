package ru.sugrobov.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sugrobov.weather.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUserId(String userId);
    Optional<User> findOneByUserIdAndPassword(String userId, String password);
}
