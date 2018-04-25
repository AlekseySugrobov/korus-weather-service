package ru.sugrobov.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sugrobov.weather.model.history.HistoryRecord;
import ru.sugrobov.weather.model.user.User;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryRecord, Long> {
    List<HistoryRecord> findByUserOrderByActionDateDesc(User user);
}
