package com.web.app.util;

import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

@PropertySource("properties/generators.properties")
public class Generators {

    private static int passwordLength;
    static {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(Generators.class.getClassLoader()
                    .getResourceAsStream("properties/generators.properties")));
            passwordLength = Integer.parseInt(properties.getProperty("passwordLength"));
        } catch (IOException e) {
            e.printStackTrace();
            passwordLength = 8;
        }
    }

    public static String generatePassword() {
        System.out.println("passwordLength = " + passwordLength);
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").substring(0, passwordLength);
    }
}
