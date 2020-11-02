package com.lms.validation.err_decorators;

import com.lms.validation.base.Error;
import com.lms.validation.base.ErrorDecorator;

public class ShortLengthErrorDecorator extends ErrorDecorator {

    Error error;

    public ShortLengthErrorDecorator(Error error) {
        this.error = error;
    }
    @Override
    public String errors(String value) {
        if(value.length() < 3)
            return this.error.errors(value) + "\n It must contain more than 3 symbols";
        else return this.error.errors(value);
    }
}
