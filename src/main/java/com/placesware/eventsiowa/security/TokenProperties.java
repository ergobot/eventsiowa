package com.placesware.eventsiowa.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenProperties {

    @Value("${token.client}")
    private String client;

    public String getClient() {
        return client;
    }

}
