package com.web.app.repository;

import com.web.app.exceptions.UserAlreadyExistsException;
import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.model.UsersDTO;

public interface UserRepository {
    void saveUser(UsersDTO usersDTO) throws UserAlreadyExistsException;
    UsersDTO checkUser(String userLogin, String userPassword);
    UsersEntity getUser(String userLogin);
}