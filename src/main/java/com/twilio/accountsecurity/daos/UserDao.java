package com.twilio.accountsecurity.daos;

import com.twilio.accountsecurity.models.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<UserModel, Integer> {

    UserModel findFirstByUsername(String username);
}
