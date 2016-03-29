package com.placesware.eventsiowa.rop;

public class SignInResponse {

    public SignInResponse(){}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean success;
}