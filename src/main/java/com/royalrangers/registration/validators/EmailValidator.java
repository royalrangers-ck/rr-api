package com.royalrangers.registration.validators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    Pattern pattern;
    Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {

        pattern = Pattern.compile(EMAIL_PATTERN);
    }


    public  boolean  validate(final String hex) {

        System.out.print("EMAIL IS :"+hex+"PAttern ise"+pattern);

        matcher = pattern.matcher(hex);
        System.out.print("MATCHER is"+matcher);
        return matcher.matches();


    }
}
