package com.twilio.accountsecurity.services;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCreator;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.rest.verify.v2.service.VerificationCheckCreator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.accountsecurity.exceptions.TokenVerificationException;

@Service
public class PhoneVerificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    private Settings settings;

    @Autowired
    public PhoneVerificationService(@Autowired Settings settings) {
        this.settings = settings;
        Twilio.init(settings.getAccountSid(), settings.getAuthToken());
    }

    public VerificationCreator getVerificationCreator(String verificationSid, String phoneNumber, String via) {
        return Verification.creator(verificationSid, phoneNumber, via);
    }

    public void start(String phoneNumber, String via) {
        this.getVerificationCreator(settings.getVerificationSid(), phoneNumber, via).create();
    }

    public VerificationCheckCreator getVerificationCheckCreator(String verificationSid, String phoneNumber, String token) {
        return VerificationCheck.creator(verificationSid, token).setTo(phoneNumber);
    }

    public void verify(String phoneNumber, String token) {
        VerificationCheck verificationCheck = this
                        .getVerificationCheckCreator(settings.getVerificationSid(), phoneNumber, token)
                        .create();
        // logAndThrow(">>>>>>" + verificationCheck.toString() + " <<<<<<< " + verificationCheck.getStatus());
        if(verificationCheck.getStatus().compareTo("approved") != 0) {
            logAndThrow("Error verifying token. ");
        }
    }

    private void logAndThrow(String message) {
        LOGGER.warn(message);
        throw new TokenVerificationException(message);
    }
}
