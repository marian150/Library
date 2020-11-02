package com.lms.validation.err_decorators;

import com.lms.validation.base.Error;
import com.lms.validation.base.ErrorDecorator;

public class LongLengthErrorDecorator extends ErrorDecorator {
    Error error;

    public LongLengthErrorDecorator(Error error) {
        this.error = error;
    }
    @Override
    public String errors(String value) {
        if (value.length() > 40) {
            return this.error.errors(value) + "\n It must not contain more than 40 symbols";
        } else return this.error.errors(value);
    }
}
