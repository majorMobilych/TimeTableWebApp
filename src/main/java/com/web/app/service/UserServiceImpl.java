package com.web.app.service;

import com.web.app.exceptions.UserAlreadyExistsException;
import com.web.app.model.CheckUserStatus;
import com.web.app.model.UsersDTO;
import com.web.app.repository.UserRepository;
import com.web.app.util.Generators;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceImpl implements UserService {
    private final Email email;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(Email email, UserRepository userRepository) {
        this.email = email;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUserAndSendPassword(String userEmail, String name) {
        String password = Generators.generatePassword();
        try {
            userRepository.saveUser(new UsersDTO(userEmail, name, password, CheckUserStatus.SUCCESS.name()));
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
        sendPassword(userEmail, password);
    }

    private void sendPassword(String userEmail, String password) {
        email.setSubject("Authentication to timeTable.project");
        try {
            email.setMsg("Your password: " + password);
            email.addTo(userEmail);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        log.debug("PASSWORD WAS SENT");
    }
}