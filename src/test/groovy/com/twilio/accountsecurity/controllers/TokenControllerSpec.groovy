package com.twilio.accountsecurity.controllers

import com.twilio.accountsecurity.exceptions.TokenVerificationException
import com.twilio.accountsecurity.services.TokenService
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.security.Principal

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class TokenControllerSpec extends Specification {
    def tokenService = Mock(TokenService)
    def tokenController = new TokenController(tokenService)

    MockMvc mockMvc = standaloneSetup(tokenController).build()

    def username = 'user name'

    def 'sms - returns 500 for TokenVerificationException'(){
        given:
        def principal = Mock(Principal)
        2 * principal.getName() >> username
        1 * tokenService.sendSmsToken(username) >> {
            throw new TokenVerificationException('message')}

        when:
        def response = mockMvc
                .perform(post('/api/token/sms').principal(principal))
                .andReturn().response

        then:
        response.status == 500
        response.contentAsString == 'message'
    }

    def 'sms - returns 200'(){
        given:
        def principal = Mock(Principal)
        2 * principal.getName() >> username
        1 * tokenService.sendSmsToken(username)

        when:
        def response = mockMvc
                .perform(post('/api/token/sms').principal(principal))
                .andReturn().response

        then:
        response.status == 200
    }

    def 'voice - returns 500 for TokenVerificationException'(){
        given:
        def principal = Mock(Principal)
        2 * principal.getName() >> username
        1 * tokenService.sendVoiceToken(username) >> {
            throw new TokenVerificationException('message')}

        when:
        def response = mockMvc
                .perform(post('/api/token/voice').principal(principal))
                .andReturn().response

        then:
        response.status == 500
        response.contentAsString == 'message'
    }

    def 'voice - returns 200'(){
        given:
        def principal = Mock(Principal)
        2 * principal.getName() >> username
        1 * tokenService.sendVoiceToken(username)

        when:
        def response = mockMvc
                .perform(post('/api/token/voice').principal(principal))
                .andReturn().response

        then:
        response.status == 200
    }

    def 'onetouch - returns 500 for TokenVerificationException'(){
        given:
        def principal = Mock(Principal)
        2 * principal.getName() >> username
        1 * tokenService.sendOneTouchToken(username) >> {
            throw new TokenVerificationException('message')}

        when:
        def response = mockMvc
                .perform(post('/api/token/onetouch').principal(principal))
                .andReturn().response

        then:
        response.status == 500
        response.contentAsString == 'message'
    }

    def 'onetouch - returns 200'(){
        given:
        def principal = Mock(Principal)
        2 * principal.getName() >> username
        1 * tokenService.sendOneTouchToken(username)

        when:
        def response = mockMvc
                .perform(post('/api/token/onetouch').principal(principal))
                .andReturn().response

        then:
        response.status == 200
    }
}
