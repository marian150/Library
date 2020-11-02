package com.lms.validation.err_decorators;

import com.lms.validation.base.Error;
import com.lms.validation.base.ErrorDecorator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlyNumbersErrorDecorator extends ErrorDecorator {

    Error error;

    public OnlyNumbersErrorDecorator(Error error) {
        this.error = error;
    }

    @Override
    public String errors(String value) {
        Pattern pattern = Pattern.compile("[0-9]+$");
        Matcher matcher = pattern.matcher(value);
        if(!matcher.find()) {
            return this.error.errors(value) + "\n It must contain only numbers";
        } else return this.error.errors(value);
    }
}
