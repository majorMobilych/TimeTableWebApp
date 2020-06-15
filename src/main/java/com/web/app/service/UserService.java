package com.web.app.service;

import org.apache.commons.mail.EmailException;

public interface UserService {
    String sendPassword(String userEmail) throws EmailException;
}
