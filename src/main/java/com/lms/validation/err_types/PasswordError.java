package com.lms.validation.err_types;

import com.lms.validation.base.Error;

public class PasswordError extends Error {

    @Override
    public String errors(String value) {
        return "Password errors:";
    }
}
