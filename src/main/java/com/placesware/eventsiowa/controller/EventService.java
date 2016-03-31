package com.placesware.eventsiowa.controller;

import com.placesware.eventsiowa.security.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventService {

//    @PreAuthorize("@customAuthorization.isDataOwner('hello')")
    @PreAuthorize("@customAuthorization.isDataOwner(user, #input)")
    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(@RequestBody String input){
        // Demo get auth and user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        // return message to confirm
        return "pax in scientia|"  + input + "|" + user.getSub() +"|" + user.getEmail();
    }

}
