package com.lms.validation.err_decorators;

import com.lms.validation.base.Error;
import com.lms.validation.base.ErrorDecorator;

public class NotNullErrorDecorator extends ErrorDecorator {

    Error error;

    public NotNullErrorDecorator(Error error) {
        this.error = error;
    }

    @Override
    public String errors(String value) {
        if (value == "") {
            return error.errors(value) + "\n It must not be empty";
        } else return  error.errors(value);
    }
}
