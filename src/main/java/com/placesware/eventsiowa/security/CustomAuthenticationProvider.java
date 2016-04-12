package com.placesware.eventsiowa.security;

import com.placesware.eventsiowa.user.domain.User;
import com.placesware.eventsiowa.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    // This would be a mongo repository to snag your user entities
    private final UserRepository userRepository;
    private final AuthenticationProperties authenticationProperties;
    private final TokenProperties tokenProperties;

//    @Autowired
    public CustomAuthenticationProvider(UserRepository userRepository, AuthenticationProperties authenticationProperties, TokenProperties tokenProperties) {
        this.userRepository = userRepository;
        this.authenticationProperties = authenticationProperties;
        this.tokenProperties = tokenProperties;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthenticationToken demoAuthentication = (CustomAuthenticationToken) authentication;

        User user = null;
        if(authenticationProperties.isSimulateUser()){
            user = new User();
            user.setEmail("test@gmail.com");
            user.setSub("12348595098");
            ArrayList<String> rights = new ArrayList<String>();
            rights.add("ADMIN");
            user.setRights(rights);
        }else{
            user = new Token().ValidateAndGetUser(demoAuthentication.getCredentials().toString(), tokenProperties.getClient());
        }
        // save user
        userRepository.save(user);

        // Create our Authentication and let Spring know about it
        Authentication auth = new CustomAuthenticationToken(user,demoAuthentication.getCredentials().toString());
        auth.setAuthenticated(true);
        // Not sure if this stays or not
//        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }

}