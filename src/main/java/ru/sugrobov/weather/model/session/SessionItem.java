package ru.sugrobov.weather.model.session;

import lombok.Data;
import ru.sugrobov.weather.identity.TokenUser;

import java.util.List;

@Data
public class SessionItem {
    private String token;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public static SessionItem mapTokenUser(TokenUser tokenUser){
        SessionItem sessionItem = new SessionItem();
        sessionItem.setFirstName(tokenUser.getUser().getFirstName());
        sessionItem.setLastName(tokenUser.getUser().getLastName());
        sessionItem.setUserId(tokenUser.getUser().getUserId());
        sessionItem.setEmail(tokenUser.getUser().getEmail());
        sessionItem.setRole(tokenUser.getRole());
        return sessionItem;
    }
}
