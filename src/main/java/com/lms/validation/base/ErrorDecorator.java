package com.lms.validation.base;

public abstract class ErrorDecorator extends Error {
    public abstract String errors(String value);
}
