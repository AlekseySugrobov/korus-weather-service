package ru.sugrobov.weather.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sugrobov.weather.model.history.HistoryRecord;
import ru.sugrobov.weather.model.history.UserAction;
import ru.sugrobov.weather.model.user.User;
import ru.sugrobov.weather.repository.HistoryRepository;
import ru.sugrobov.weather.service.IHistoryService;

@Service
@Slf4j
public class HistoryService implements IHistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Override
    public void writeAction(User user, UserAction userAction, String description) {
        log.info("writing action: user {}, action {}, description {}", user != null ? user.getUserId() : "Anonymus", userAction.getTitle(), description);
        HistoryRecord record = new HistoryRecord(user, userAction, description);
        historyRepository.save(record);
    }
}
