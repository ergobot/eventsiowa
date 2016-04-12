package com.placesware.eventsiowa.config;

import com.placesware.eventsiowa.security.*;
import com.placesware.eventsiowa.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProperties authenticationProperties;

//    @Autowired
//    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProperties tokenProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if(authenticationProperties.isAuthenticate()) {

            http.
                    csrf().disable().
//                    authenticationProvider(customAuthenticationProvider). // cant get the custom provider to work
                    sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                    and().
                    authorizeRequests().anyRequest().permitAll().
                    and().
                    anonymous().disable();
//                exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());
//
            http.addFilterBefore(new CustomAuthenticationFilter(), BasicAuthenticationFilter.class);
        }

    }

//    @Autowired
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(customAuthenticationProvider);
//    }


//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new CustomAuthenticationProvider(userRepository,authenticationProperties,tokenProperties));
//    }

}