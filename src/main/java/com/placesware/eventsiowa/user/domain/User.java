package com.placesware.eventsiowa.user.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;
    private String sub;

    private String email;

    private List<String> rights;

    public List<String> getRights() {
        return rights;
    }

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

    public void setRights(List<String> rights) {
        this.rights = rights;
    }

    public User() {}



}