package com.example.coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughQuestionsException extends RuntimeException {
    public NotEnoughQuestionsException() {
    }

    public NotEnoughQuestionsException(String message) {
        super(message);
    }

    public NotEnoughQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughQuestionsException(Throwable cause) {
        super(cause);
    }

    public NotEnoughQuestionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
