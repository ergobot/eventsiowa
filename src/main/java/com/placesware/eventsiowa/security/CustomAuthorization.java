package com.placesware.eventsiowa.security;


import com.placesware.eventsiowa.controller.user.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;

@Configuration
public class CustomAuthorization implements PermissionEvaluator {

    public boolean isDataOwner(Authentication authentication, String eventId){
        User user = (User) authentication.getPrincipal();



        return true;
    }

    public boolean hasPermission(Authentication authentication){

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

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
