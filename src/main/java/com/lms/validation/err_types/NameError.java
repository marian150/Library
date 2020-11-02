package com.lms.validation.err_types;

import com.lms.validation.base.Error;

public class NameError extends Error {
    @Override
    public String errors(String value) {
        return "Name errors:";
    }
}
