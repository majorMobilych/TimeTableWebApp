package com.web.app.validator;

import com.web.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@PropertySource("properties/errors/error_messages.properties")
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    @Value("${not.empty}")
    private String NOT_EMPTY;

    @Value("${size.user.form.username}")
    private String SIZE_USER_FORM_USERNAME;

    @Value("@{duplicate.user.form.username}")
    private String DUPLICATE_USER_FORM_USERNAME;

    @Value("@{size.user.form.password}")
    private String SIZE_USER_FORM_PASSWORD;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", NOT_EMPTY);
        if (user.getUsername().length() < 5 || user.getUsername().length() > 30) {
            errors.rejectValue("username", SIZE_USER_FORM_USERNAME);
        }
        if (userRepository.getUser(user.getUsername()) != null) {
            errors.rejectValue("username", DUPLICATE_USER_FORM_USERNAME);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", NOT_EMPTY);
        if (user.getPassword().length() < 8 || user.getPassword().length() > 16) {
            errors.rejectValue("password", SIZE_USER_FORM_USERNAME);
        }
    }
}
