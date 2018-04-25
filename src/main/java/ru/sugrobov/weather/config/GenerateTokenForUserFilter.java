package ru.sugrobov.weather.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.sugrobov.weather.identity.TokenUser;
import ru.sugrobov.weather.identity.TokenUtil;
import ru.sugrobov.weather.model.history.UserAction;
import ru.sugrobov.weather.model.response.OperationResponse;
import ru.sugrobov.weather.model.session.SessionItem;
import ru.sugrobov.weather.model.session.SessionResponse;
import ru.sugrobov.weather.repository.UserRepository;
import ru.sugrobov.weather.service.IHistoryService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class GenerateTokenForUserFilter extends AbstractAuthenticationProcessingFilter{
    private TokenUtil tokenUtil;
    private IHistoryService historyService;
    private UserRepository userRepository;
    protected GenerateTokenForUserFilter(String urlMapping, AuthenticationManager authenticationManager,
                                         TokenUtil tokenUtil, IHistoryService historyService, UserRepository userRepository){
        super(new AntPathRequestMatcher(urlMapping));
        setAuthenticationManager(authenticationManager);
        this.tokenUtil = tokenUtil;
        this.historyService = historyService;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, JSONException {
        try{
            String jsonString = IOUtils.toString(request.getInputStream(), "UTF-8");
            JSONObject userJSON = new JSONObject(jsonString);
            String username = userJSON.getString("username");
            String password = userJSON.getString("password");
            String browser = request.getHeader("User-Agent")!= null?request.getHeader("User-Agent"):"";
            String ip = request.getRemoteAddr();
            String info = String.format("ip:%s browser:%s username:%s",ip,browser,username);
            log.info(info);
            historyService.writeAction(userRepository.findOneByUserId(username).orElse(null), UserAction.LOGIN_ATTEMPT, info);
            final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            return getAuthenticationManager().authenticate(authToken);
        }
        catch( JSONException | AuthenticationException e){
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication (HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authToken) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authToken);
        TokenUser tokenUser = (TokenUser)authToken.getPrincipal();
        SessionItem sessionItem = SessionItem.mapTokenUser(tokenUser);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String tokenString = this.tokenUtil.createTokenForUser(tokenUser);
        sessionItem.setToken(tokenString);
        historyService.writeAction(tokenUser.getUser(), UserAction.LOGIN, "Успешный вход");
        SessionResponse sessionResponse = new SessionResponse(OperationResponse.ResponseStatusEnum.SUCCESS,
                "Successfully logged in", sessionItem);
        String jsonRespString = ow.writeValueAsString(sessionResponse);
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(jsonRespString);
        res.getWriter().flush();
        res.getWriter().close();
    }
}

