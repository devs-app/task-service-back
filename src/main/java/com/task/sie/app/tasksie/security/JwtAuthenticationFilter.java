package com.task.sie.app.tasksie.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        AuthCredential authCredential = new AuthCredential();

        try {
            authCredential = new ObjectMapper().readValue(request.getReader(), AuthCredential.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken usernamePath = new UsernamePasswordAuthenticationToken(
                authCredential.getUsername(),
                authCredential.getPassword(),
                Collections.emptyList()
        );
    return  getAuthenticationManager().authenticate(usernamePath);
}

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        String token = TokenUtils.generateToken(userDetails.getUsername(), userDetails.getEmail());

        response.addHeader("Authorization", "Bearer " + token);

        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
