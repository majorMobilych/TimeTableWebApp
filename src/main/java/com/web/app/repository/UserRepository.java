package com.web.app.repository;

import com.web.app.model.UsersDTO;

public interface UserRepository {
    void saveUser(UsersDTO usersDTO);
    UsersDTO checkUser(String userLogin, String userPassword);
}
