package com.placesware.eventsiowa;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.placesware.eventsiowa.security.DemoAuthenticationToken;
import com.placesware.eventsiowa.security.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class DemoAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("X-Authorization");

        User user = Token.ValidateAndGetUser(token);

        // The token is 'valid' so magically get a user id from it
        String sub = "testsub";//getUserIdFromToken(xAuth);

        // Create our Authentication and let Spring know about it
        Authentication auth = new DemoAuthenticationToken(user,token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}