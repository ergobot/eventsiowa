package com.placesware.eventsiowa.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("writeSecurityService")
public class CustomAuthorization {
    public boolean hasPermission(Authentication authentication, String eventId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        // Check if user is information owner
//        if(isEventOwner(user.getSub(),eventId)){
//              return true;
//        }

        // Check if user has a role that allows write
        for(String right: user.getRights()) {
            if ("MODERATOR".equals(right)){
                return true;
            }else if("ADMIN".equals(right)){
                return true;
            }
        }
        return false;
    }
}
