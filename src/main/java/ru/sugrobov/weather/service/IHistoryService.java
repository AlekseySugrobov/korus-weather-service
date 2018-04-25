package ru.sugrobov.weather.service;

import ru.sugrobov.weather.model.history.UserAction;
import ru.sugrobov.weather.model.user.User;

public interface IHistoryService {
    void writeAction(User user, UserAction userAction, String description);
}
