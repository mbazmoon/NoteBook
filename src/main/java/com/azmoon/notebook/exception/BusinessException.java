package com.azmoon.notebook.exception;

import java.io.Serial;

public abstract class BusinessException extends Exception {

    @Serial
    private static final long serialVersionUID = -1755015356317401782L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
