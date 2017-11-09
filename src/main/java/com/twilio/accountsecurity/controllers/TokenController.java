package com.twilio.accountsecurity.controllers;

import com.twilio.accountsecurity.controllers.requests.VerifyTokenRequest;
import com.twilio.accountsecurity.exceptions.TokenVerificationException;
import com.twilio.accountsecurity.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/api/token")
public class TokenController {

    private TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "sms", method = RequestMethod.POST)
    public ResponseEntity sms(HttpServletRequest request) {
        return sendToken(request, (user) -> tokenService.sendSmsToken(user));
    }

    @RequestMapping(value = "voice", method = RequestMethod.POST)
    public ResponseEntity voice(HttpServletRequest request) {
        return sendToken(request, (user) -> tokenService.sendVoiceToken(user));
    }

    @RequestMapping(value = "onetouch", method = RequestMethod.POST)
    public ResponseEntity onetouch(HttpServletRequest request) {
        return sendToken(request, (user) -> tokenService.sendOneTouchToken(user));
    }

    @RequestMapping(value = "verify", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity verify(@Valid @RequestBody VerifyTokenRequest requestBody,
                                 HttpServletRequest request, HttpSession session) {
        try {
            tokenService.verify(request.getUserPrincipal().getName(), requestBody);
            session.setAttribute("authy", true);

            return ResponseEntity.ok().build();
        } catch (TokenVerificationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    private ResponseEntity<? extends Object> sendToken(HttpServletRequest request,
                                                       Consumer<String> consumer) {
        try {
            consumer.accept(request.getUserPrincipal().getName());
            return ResponseEntity.ok().build();
        } catch (TokenVerificationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
