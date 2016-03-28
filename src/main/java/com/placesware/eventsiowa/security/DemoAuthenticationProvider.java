package com.placesware.eventsiowa.security;

import com.placesware.eventsiowa.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class DemoAuthenticationProvider implements AuthenticationProvider {

    // This would be a JPA repository to snag your user entities
    private final UserRepository userRepository;

    @Autowired
    public DemoAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        DemoAuthenticationToken demoAuthentication = (DemoAuthenticationToken) authentication;
        User partialUser = ((User)demoAuthentication.getPrincipal());
        User user = userRepository.findBySub(partialUser.getSub());

        if(user == null){
//            throw new UnknownUserException("Could not find user with ID: " + demoAuthentication.getId());
            throw new InternalAuthenticationServiceException("Could not find user with ID: " + partialUser.getSub());
        }



        return new DemoAuthenticationToken(user,demoAuthentication.getPrincipal());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return DemoAuthenticationToken.class.isAssignableFrom(authentication);
    }

}