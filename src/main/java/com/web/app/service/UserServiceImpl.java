package com.web.app.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UserServiceImpl implements UserService {
    private final Email email;

    @Autowired
    public UserServiceImpl(Email email) {
        this.email = email;
    }

    @Override
    public String sendPassword(String userEmail) throws EmailException {
        email.setSubject("Authentication to timeTable.project");
        String password = generatePassword();
        email.setMsg("Your password: " + password);
        email.addTo(userEmail);
        log.debug("PASSWORD WAS SENT");
        email.send();
        return password;
    }

    /* Generate random password */
    private static String generatePassword() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").substring(0, 10);
    }
}
