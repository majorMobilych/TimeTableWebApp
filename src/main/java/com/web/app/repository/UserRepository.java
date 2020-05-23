package com.web.app.repository;

import com.web.app.hibernate.entity.UsersEntity;
import com.web.app.model.UserDTO;

public interface UserRepository {
    void saveUser(UserDTO userDTO);
    UsersEntity getUser(String userLogin, String userPassword);
}
