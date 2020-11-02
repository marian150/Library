package com.lms.validation.err_decorators;

import com.lms.validation.base.Error;
import com.lms.validation.base.ErrorDecorator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlyCharErrorDecorator extends ErrorDecorator {

    Error error;

    public OnlyCharErrorDecorator(Error error) {
        this.error = error;
    }

    @Override
    public String errors(String value) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(value);
        if(!matcher.find()) {
            return error.errors(value) + "\n It must contain only characters";
        } else return error.errors(value);
    }
}
