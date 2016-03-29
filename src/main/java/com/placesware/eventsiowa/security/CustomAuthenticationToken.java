package com.placesware.eventsiowa.security;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author malike_st
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {



    private final Object principal;
    private final Object credentials;
    private final boolean client;

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.client = true;
        super.setAuthenticated(true);
    }

    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.client = true;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public boolean isClient() {
        return client;
    }

}