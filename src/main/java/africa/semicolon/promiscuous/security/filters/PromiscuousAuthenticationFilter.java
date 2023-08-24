package africa.semicolon.promiscuous.security.filters;

import africa.semicolon.promiscuous.dtos.requests.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor

public class PromiscuousAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //1. extract the authentication credentials from the request.
            InputStream inputStream = request.getInputStream();
           LoginRequest loginRequest = objectMapper.readValue(inputStream, LoginRequest.class);

           String email = loginRequest.getEmail();
           String password = loginRequest.getPassword();
           //2. create an authentication object that is not yet authenticated
           Authentication authentication = new UsernamePasswordAuthenticationToken(email, password); //email is principal, password is credential.
           //3. delegates authentication responsibility of the authentication object to the authentication manager
           Authentication authenticationResult = authenticationManager.authenticate(authentication);
           //4.

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
