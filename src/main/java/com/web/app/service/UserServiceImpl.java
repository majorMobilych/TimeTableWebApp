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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceImpl implements UserService {
    private final Email email;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(Email email, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.email = email;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void saveUserAndSendPassword(String userEmail, String name) {
        String password = Generators.generatePassword();
        try {
            userRepository.saveUser(new UsersDTO(userEmail, name, bCryptPasswordEncoder.encode(password),
                    CheckUserStatus.SUCCESS.name()));
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
        sendPassword(userEmail, password);
    }

    private void sendPassword(String userEmail, String password) {
        email.setSubject("Timetable authentication");
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