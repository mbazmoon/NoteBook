package com.azmoon.notebook.exception;

public class RoleNotFoundException extends BusinessException {

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(Throwable cause) {
        super(cause);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
