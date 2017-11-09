package com.twilio.accountsecurity.controllers.requests;

import javax.validation.constraints.NotNull;

public class VerifyTokenRequest {

    @NotNull
    private String token;

    public VerifyTokenRequest(String token) {
        this.token = token;
    }

    public VerifyTokenRequest() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
