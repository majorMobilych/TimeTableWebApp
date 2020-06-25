package com.web.app.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("properties/techemail.properties")
@Slf4j
public class ServiceConfiguration {

    @Value("${application.hostname}")
    private String hostName;

    @Value("${application.smtpport}")
    private String SMTPPort;

    @Value("${application.email}")
    private String email;

    @Value("${application.password}")
    private String password;

    @Bean
    public Email email() throws EmailException {
        Email simpleEmail = new SimpleEmail();
        simpleEmail.setHostName(hostName);
        simpleEmail.setSmtpPort(Integer.parseInt(SMTPPort));
        simpleEmail.setAuthenticator(new DefaultAuthenticator(email, password));
        simpleEmail.setSSLOnConnect(true);
        simpleEmail.setFrom(email);
        log.debug("email @Bean was created");
        return simpleEmail;
    }
}
