package com.twilio.accountsecurity.controllers.requests;

import javax.validation.constraints.NotNull;

public class PhoneVerificationStartRequest {

    @NotNull
    private String phoneNumber;
    @NotNull
    private String countryCode;
    @NotNull
    private String via;

    public PhoneVerificationStartRequest() {
    }

    public PhoneVerificationStartRequest(String phoneNumber, String countryCode, String via) {
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.via = via;
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

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }
}
