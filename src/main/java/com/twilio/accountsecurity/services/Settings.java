package com.twilio.accountsecurity.services;

import org.springframework.stereotype.Service;

@Service
public class Settings {

    public String getAuthyId(){
        return System.getenv("ACCOUNT_SECURITY_API_KEY");
    }
}
