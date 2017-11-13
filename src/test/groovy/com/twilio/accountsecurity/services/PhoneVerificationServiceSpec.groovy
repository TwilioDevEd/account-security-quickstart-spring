package com.twilio.accountsecurity.services

import com.authy.AuthyApiClient
import com.authy.api.Params
import com.authy.api.PhoneVerification
import com.authy.api.Verification
import com.twilio.accountsecurity.exceptions.TokenVerificationException
import spock.lang.Specification
import spock.lang.Subject

class PhoneVerificationServiceSpec extends Specification {

    AuthyApiClient authyApiClient = Mock()
    PhoneVerification phoneVerification = Mock()

    @Subject PhoneVerificationService phoneVerificationService

    static phone = '1'
    static countryCode = '2'
    static via = 'sms'
    static token = 'token'

    def setup() {
        phoneVerificationService = new PhoneVerificationService(authyApiClient)
        1 * authyApiClient.getPhoneVerification() >> phoneVerification
    }

    def "start - success"() {
        given:
        1 * phoneVerification.start(phone, countryCode, via, _ as Params) >>
                new Verification(200, '', '')

        when:
        phoneVerificationService.start(countryCode, phone, via)

        then:
        notThrown TokenVerificationException
    }

    def "start - error"() {
        given:
        1 * phoneVerification.start(phone, countryCode, via, _ as Params) >>
                new Verification(400, '', '')

        when:
        phoneVerificationService.start(countryCode, phone, via)

        then:
        TokenVerificationException e = thrown()
        e.getMessage() == 'Error requesting phone verification. '
    }

    def "verify - success"() {
        given:
        1 * phoneVerification.check(phone, countryCode, token) >>
                new Verification(200, '', '')

        when:
        phoneVerificationService.verify(countryCode, phone, token)

        then:
        notThrown TokenVerificationException
    }

    def "verify - error"() {
        given:
        1 * phoneVerification.check(phone, countryCode, token) >>
                new Verification(400, '', '')

        when:
        phoneVerificationService.verify(countryCode, phone, token)

        then:
        TokenVerificationException e = thrown()
        e.getMessage() == 'Error verifying token. '
    }
}
