package com.lms.validation.err_types;

import com.lms.validation.base.Error;

public class PhoneError extends Error {
    @Override
    public String errors(String value) {
        return "Phone errors:";
    }
}
