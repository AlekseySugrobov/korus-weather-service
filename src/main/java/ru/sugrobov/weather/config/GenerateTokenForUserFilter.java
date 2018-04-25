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
import ru.sugrobov.weather.model.response.OperationResponse;
import ru.sugrobov.weather.model.session.SessionItem;
import ru.sugrobov.weather.model.session.SessionResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class GenerateTokenForUserFilter extends AbstractAuthenticationProcessingFilter{
    private TokenUtil tokenUtil;
    protected GenerateTokenForUserFilter(String urlMapping, AuthenticationManager authenticationManager, TokenUtil tokenUtil){
        super(new AntPathRequestMatcher(urlMapping));
        setAuthenticationManager(authenticationManager);
        this.tokenUtil = tokenUtil;
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
            log.info("\nip:{} \nbrowser:{} \n----",ip,browser);
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
        SessionResponse sessionResponse = new SessionResponse(OperationResponse.ResponseStatusEnum.SUCCESS,
                "Successfully logged in", sessionItem);
        String jsonRespString = ow.writeValueAsString(sessionResponse);
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(jsonRespString);
        res.getWriter().flush();
        res.getWriter().close();
    }
}

