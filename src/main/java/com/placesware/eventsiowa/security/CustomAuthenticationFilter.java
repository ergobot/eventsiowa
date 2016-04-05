package com.placesware.eventsiowa.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.placesware.eventsiowa.controller.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("X-Authorization");

//        User user = Token.ValidateAndGetUser(token);
        // test user
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setSub("12348595098");
        ArrayList<String> rights = new ArrayList<String>();
        rights.add("ADMIN");
        user.setRights(rights);

        // The token is 'valid' so magically get a user id from it
        String sub = "testsub";//getUserIdFromToken(xAuth);

        // Create our Authentication and let Spring know about it
        Authentication auth = new CustomAuthenticationToken(user,token);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}