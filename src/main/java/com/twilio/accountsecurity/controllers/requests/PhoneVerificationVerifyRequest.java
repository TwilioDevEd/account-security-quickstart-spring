package com.twilio.accountsecurity.controllers.requests;

import javax.validation.constraints.NotNull;

public class PhoneVerificationVerifyRequest {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String countryCode;
    @NotNull
    private String token;

    public PhoneVerificationVerifyRequest() {
    }

    public PhoneVerificationVerifyRequest(String phoneNumber, String countryCode, String token) {
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.token = token;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
