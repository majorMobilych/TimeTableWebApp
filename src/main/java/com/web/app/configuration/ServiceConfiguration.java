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

/**
 * Class returns Email object
 */
@Configuration
@Slf4j
@PropertySource("properties/techemail.properties")
public class ServiceConfiguration {
    @Value("${application.email}")
    private String email;
    @Value("${application.password}")
    private String password;

    @Bean
    public Email email() throws EmailException {
        Email simpleEmail = new SimpleEmail();
        simpleEmail.setHostName("smtp.yandex.com");
        simpleEmail.setSmtpPort(4651);
        simpleEmail.setAuthenticator(new DefaultAuthenticator(email, password));
        simpleEmail.setSSLOnConnect(true);
        simpleEmail.setFrom(email);
        log.debug("email @Bean was created");
        return simpleEmail;
    }
}
