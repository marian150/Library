package com.lms.validation.err_types;

import com.lms.validation.base.Error;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailError extends Error {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public EmailError () {}
    @Override
    public String errors(String value) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
        if (!matcher.find())
            return "This is not a valid email address";
        else return null;
    }
}
