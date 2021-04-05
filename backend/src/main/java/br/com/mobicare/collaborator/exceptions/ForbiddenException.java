package br.com.mobicare.collaborator.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {}

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}