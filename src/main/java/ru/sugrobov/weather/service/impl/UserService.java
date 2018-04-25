package ru.sugrobov.weather.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sugrobov.weather.identity.TokenUser;
import ru.sugrobov.weather.model.user.User;
import ru.sugrobov.weather.service.IUserService;

@Service
public class UserService implements IUserService{
    @Override
    public User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getDetails() instanceof TokenUser){
            return ((TokenUser) auth.getDetails()).getUser();
        } else {
            return null;
        }
    }
}
