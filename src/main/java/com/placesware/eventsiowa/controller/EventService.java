package com.placesware.eventsiowa.controller;

import com.placesware.eventsiowa.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventService {

    @RequestMapping("/echo")
    public String echo(){
        // Demo get auth and user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        // return message to confirm
        return "pax in scientia|" + user.getSub() +"|" + user.getEmail();
    }

}
