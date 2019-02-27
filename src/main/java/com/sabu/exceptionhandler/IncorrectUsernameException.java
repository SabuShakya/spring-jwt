package com.sabu.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectUsernameException extends RuntimeException {
    private ExceptionJSON exceptionJSON;

    public IncorrectUsernameException(String errorMsg, String developerMessage) {
        super(errorMsg);
        exceptionJSON= new ExceptionJSON();
        exceptionJSON.setErrorMsg(errorMsg);
        exceptionJSON.setDeveloperMessage(developerMessage);
    }
}
