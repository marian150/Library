package com.lms.validation;

public abstract class ErrorDecorator extends Error{
    public abstract String errors(String value);
}
