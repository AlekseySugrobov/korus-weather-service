package ru.sugrobov.weather.model.history;

public enum UserAction {
    LOGIN_ATTEMPT("Попытка входа"), LOGIN("Вход"), WEATHER_REQUEST("Запрос погоды"), LOGOUT ("Выход");

    private String title;

    UserAction(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
