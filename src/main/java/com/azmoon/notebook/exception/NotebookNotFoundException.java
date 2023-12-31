package com.azmoon.notebook.exception;

public class NotebookNotFoundException extends BusinessException {

    public NotebookNotFoundException(String message) {
        super(message);
    }

    public NotebookNotFoundException(Throwable cause) {
        super(cause);
    }

    public NotebookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
