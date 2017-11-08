package com.twilio.accountsecurity.services

import com.authy.AuthyApiClient
import com.authy.api.User
import com.authy.api.Users
import com.twilio.accountsecurity.controllers.UserRegisterRequest
import com.twilio.accountsecurity.daos.UserDao
import com.twilio.accountsecurity.exceptions.UserExistsException
import com.twilio.accountsecurity.models.UserModel
import com.twilio.accountsecurity.models.UserRoles
import com.twilio.accountsecurity.services.RegisterService
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

class RegisterServiceSpec extends Specification {

    @Subject RegisterService service

    PasswordEncoder passwordEncoder = Mock()
    UserDao userDao = Mock()
    AuthyApiClient authyApiClient = Mock()
    Users users = Mock()

    def username = 'username'
    def authyId = 123
    def request = new UserRegisterRequest(username, 'email', 'password', '1', '1234567')

    def setup() {
        service = new RegisterService(userDao, passwordEncoder, authyApiClient)
    }

    def 'register should fail when the username already exist'() {
        given:
        1 * userDao.findFirstByUsername(username) >> new UserModel()

        when:
        service.register(request)

        then:
        thrown UserExistsException
    }

    def 'register should save new user'() {
        given:
        def authyUser = new User()
        authyUser.setId(authyId)
        def expectedUser = request.toModel()
        expectedUser.setAuthyId(authyId)
        expectedUser.setRole(UserRoles.ROLE_USER)
        1 * userDao.findFirstByUsername(username) >> null
        1 * userDao.save(expectedUser)
        1 * authyApiClient.getUsers() >> users
        1 * users.createUser(request.email, request.phoneNumber, request.countryCode) >>
                authyUser

        when:
        service.register(request)

        then:
        notThrown UserExistsException
    }

}