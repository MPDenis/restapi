package com.example.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldValidationError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public FieldValidationError() {
    }

    public FieldValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
