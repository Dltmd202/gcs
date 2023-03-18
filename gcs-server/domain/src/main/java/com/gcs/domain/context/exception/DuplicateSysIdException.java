package com.gcs.domain.context.exception;

public class DuplicateSysIdException extends RuntimeException {
    public DuplicateSysIdException() {
    }

    public DuplicateSysIdException(String message) {
        super(message);
    }

    public DuplicateSysIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateSysIdException(Throwable cause) {
        super(cause);
    }

    public DuplicateSysIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
