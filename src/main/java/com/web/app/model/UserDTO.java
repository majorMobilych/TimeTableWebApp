package com.web.app.model;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Used for storing info about new-registered user;
 * When user signs up, we need to get his (desired) userName and email;
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /* TODO: как насчиет написать тредсейф синглион, переходящий от юзерСервис к юзерЭнтити
     */
    @NonNull
    private String userEmail;
    @Nullable
    private String userName;
    @Nullable
    private String userPassword;
}
