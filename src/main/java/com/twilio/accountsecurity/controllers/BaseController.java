package com.twilio.accountsecurity.controllers;

import com.twilio.accountsecurity.exceptions.PhoneVerificationException;
import com.twilio.accountsecurity.exceptions.TokenVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface BaseController {

    default ResponseEntity<? extends Object> runWithCatch(Runnable runnable, HttpStatus status) {
        try {
            runnable.run();
            return ResponseEntity.ok().build();
        } catch (PhoneVerificationException | TokenVerificationException e) {
            return ResponseEntity.status(status)
                    .body(e.getMessage());
        }
    }

    default ResponseEntity<? extends Object> runWithCatch(Runnable runnable) {
        return this.runWithCatch(runnable, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
