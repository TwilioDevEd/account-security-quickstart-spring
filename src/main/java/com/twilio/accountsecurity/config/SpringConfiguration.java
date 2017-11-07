package com.twilio.accountsecurity.config;

import com.authy.AuthyApiClient;
import com.twilio.accountsecurity.services.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfiguration {

    @Autowired
    private Settings settings;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthyApiClient authyApiClient() {
        return new AuthyApiClient(settings.getAuthyId());
    }
}
