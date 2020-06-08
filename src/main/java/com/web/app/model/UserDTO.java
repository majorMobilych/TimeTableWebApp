package com.web.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * Used for storing info about new-registered user;
 * When user signs up, we need to get his (desired) userName and email;
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String login;
    @Nullable
    private String name;
    @Nullable
    private String password;
}
