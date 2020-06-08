package com.web.app.repository;

import com.web.app.exceptions.IncorrectPasswordException;
import com.web.app.exceptions.NotExistingUserException;
import com.web.app.model.UserDTO;

public interface UserRepository {
    void saveUser(UserDTO userDTO);
    UserDTO checkUser(String userLogin, String userPassword);
}
