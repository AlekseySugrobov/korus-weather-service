package ru.sugrobov.weather.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sugrobov.weather.model.user.User;
import ru.sugrobov.weather.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public final TokenUser loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException{
        final User user = userRepository.findOneByUserId(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        TokenUser currentUser;
        if(user.isActive()){
            currentUser = new TokenUser(user);
        } else {
            throw new DisabledException("User is disabled");
        }
        detailsChecker.check(currentUser);
        return currentUser;
    }
}
