package com.web.app.repository;

import com.web.app.model.UserDTO;

public interface UserRepository {
    void saveUser(UserDTO userDTO);
    UserDTO checkUser(String userLogin, String userPassword);
}
