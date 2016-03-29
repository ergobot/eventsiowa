package com.placesware.eventsiowa.security;

import java.util.List;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;
    private String sub;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    private String email;

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }

    private List<String> rights;

    public User() {}



}