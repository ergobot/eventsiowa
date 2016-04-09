package com.placesware.eventsiowa.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationProperties {

    @Value("${authentication.authenticate}")
    private boolean authenticate;

    public boolean isAuthenticate() {
        return authenticate;
    }

    @Value("${authentication.simulateUser}")
    private boolean simulateUser;

    public boolean isSimulateUser() {
        return simulateUser;
    }

}
