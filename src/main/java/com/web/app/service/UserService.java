package com.web.app.service;

import org.apache.commons.mail.EmailException;

public interface UserService {
    /**
     * Send password to newly-registered user
     */
    String sendPassword(String userEmail) throws EmailException;
}
