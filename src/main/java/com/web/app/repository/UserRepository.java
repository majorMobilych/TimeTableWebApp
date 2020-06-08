package com.web.app.repository;

import com.web.app.exceptions.IncorrectPasswordException;
import com.web.app.exceptions.NotExistingUserException;
import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.model.UserDTO;

public interface UserRepository {
    void saveUser(UserDTO userDTO);
    UsersEntity checkUser(String userLogin, String userPassword) throws NotExistingUserException, IncorrectPasswordException;
}
