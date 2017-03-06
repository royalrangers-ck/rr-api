package com.royalrangers.utils;

import com.royalrangers.bean.UserForm;
import com.royalrangers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {

    @Autowired
    private UserService userService;

    EmailValidator emailValidator = new EmailValidator();

    public void validate(UserForm user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 50) {
            errors.rejectValue("username", "Size.userForm.username");
        } else {
            // TODO Code is duplicated!
            String expression = "^[a-zA-Z0-9]*$";
            CharSequence inputStr = user.getUsername();
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher;
            matcher = pattern.matcher(inputStr);
            if (!matcher.matches()) {
                errors.rejectValue("username", "Invalid.userForm.username");
            }
        }
        if (userService.findByUserEmail(user.getEmail()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 3 || user.getPassword().length() > 8) {
            errors.rejectValue("password", "Size.userForm.password");
        } else {
            // TODO Code is duplicated!
            String expression = "^[a-zA-Z0-9]*$";
            CharSequence inputStr = user.getPassword();
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (!matcher.matches()) {
                errors.rejectValue("password", "Invalid.userForm.password");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comfirmpassword", "NotEmpty");
        if (user.getComfirmpassword().length() < 3 || user.getPassword().length() > 8) {
            errors.rejectValue("confirmpassword", "Size.userForm.password");
        } else {
            // TODO Code is duplicated! Move it to some method that accepts two parameters: field & message
            String expression = "^[a-zA-Z0-9]*$";
            CharSequence inputStr = user.getPassword();
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (!matcher.matches()) {
                errors.rejectValue("comfirmpassword", "Invalid.userForm.confirmpassword");
            }
        }

        if (!user.getComfirmpassword().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        boolean validEmail = emailValidator.validate(user.getEmail());
        if (!validEmail) {
            errors.rejectValue("email", "Format.userForm.email");
        }

        if (userService.findByUserEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.userEmail");
        }
    }
}

