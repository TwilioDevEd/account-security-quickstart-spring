package com.twilio.accountsecurity.services

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCreator;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.rest.verify.v2.service.VerificationCheckCreator;

import com.twilio.accountsecurity.exceptions.TokenVerificationException
import com.fasterxml.jackson.databind.ObjectMapper;
import spock.lang.Specification
import spock.lang.Subject

class PhoneVerificationServiceSpec extends Specification {

    Settings settings = Mock()

    @Subject PhoneVerificationService phoneVerificationService

    static verificationSid = 'VAXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'
    static phone = '1'
    static via = 'sms'
    static token = 'token'

    def setup() {
        1 * settings.getAccountSid() >> 'ACCOUNT_SID'
        1 * settings.getAuthToken() >> 'AUTH_TOKEN'
        1 * settings.getVerificationSid() >> verificationSid
        // Cannot simply instantiate because we need to mock the getVerificationCreator method later
        phoneVerificationService = Spy(PhoneVerificationService, constructorArgs: [settings])
    }

    def "start - success"() {
        given:
        1 * phoneVerificationService.getVerificationCreator(verificationSid, phone, via) >> Stub(VerificationCreator) {
            create() >> null
        }

        when:
        phoneVerificationService.start(phone, via)

        then:
        notThrown Exception
    }

    def "start - error"() {
        given:
        1 * phoneVerificationService.getVerificationCreator(verificationSid, phone, via) >> Stub(VerificationCreator) {
            create() >> { throw new Exception('Error requesting phone verification. ') }
        }

        when:
        phoneVerificationService.start(phone, via)

        then:
        Exception e = thrown()
        e.getMessage() == 'Error requesting phone verification. '
    }

    /*def "verify - success"() {
        given:
        VerificationCheck verificationCheck = Mock()
        1 * verificationCheck.getStatus() >> 'approved'
        1 * phoneVerificationService.getVerificationCheckCreator(verificationSid, phone, token) >> Stub(VerificationCheckCreator) {
            create() >> verificationCheck
        }

        when:
        phoneVerificationService.verify(phone, token)

        then:
        notThrown Exception
    }

    def "verify - error"() {
        given:
        1 * phoneVerificationService.getVerificationCheckCreator(verificationSid, phone, token) >> Stub(VerificationCheckCreator) {
            create() >> Stub(VerificationCheck) {
                getStatus() >> 'expired'
            }
        }

        when:
        phoneVerificationService.verify(phone, token)

        then:
        Exception e = thrown()
        e.getMessage() == 'Error verifying token. '
    }*/
}
