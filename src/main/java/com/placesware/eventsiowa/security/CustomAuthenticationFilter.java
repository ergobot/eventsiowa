package com.placesware.eventsiowa.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.placesware.eventsiowa.config.SecurityConfig;
import com.placesware.eventsiowa.user.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.placesware.eventsiowa.user.data.UserRepository;

public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        UserRepository userRepository = WebApplicationContextUtils.
                getRequiredWebApplicationContext(request.getServletContext()).
                getBean(UserRepository.class);

        TokenProperties tokenProperties = WebApplicationContextUtils.
                getRequiredWebApplicationContext(request.getServletContext()).
                getBean(TokenProperties.class);

        AuthenticationProperties authenticationProperties = WebApplicationContextUtils.
                getRequiredWebApplicationContext(request.getServletContext()).
                getBean(AuthenticationProperties.class);

        String token = request.getHeader("X-Authorization");



        User user = new User();
        if(authenticationProperties.isSimulateUser()){
            user = new User();
            user.setEmail("test@gmail.com");
            user.setSub("12348595098");
            ArrayList<String> rights = new ArrayList<String>();
            rights.add("ADMIN");
            user.setRights(rights);
        }else{
            user = new Token().ValidateAndGetUser(token, tokenProperties.getClient());
        }
        // save user
        userRepository.save(user);

        // Create our Authentication and let Spring know about it
        Authentication auth = new CustomAuthenticationToken(user,token);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}