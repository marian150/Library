package com.lms.validation;

public class PasswordError extends Error{

    @Override
    public String errors(String value) {
        return "Password errors:";
    }
}
