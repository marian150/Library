package com.lms.validation.err_decorators;

import com.lms.validation.base.Error;
import com.lms.validation.base.ErrorDecorator;

public class UpperErrorDecorator extends ErrorDecorator {

    Error error;

    public UpperErrorDecorator(Error error) {
        this.error = error;
    }
    @Override
    public String errors(String value) {
        char c;
        boolean flag = false;
        for (int i = 0; i < value.length(); i++) {
            c = value.charAt(i);
            if(Character.isUpperCase(c)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return this.error.errors(value) + "\n It must contain at least one Upper case character";
        } else return this.error.errors(value);
    }
}
