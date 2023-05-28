package io.deffun.doh;

public final class ClientInvocationException extends RuntimeException {
    public ClientInvocationException(Throwable cause) {
        super(cause);
    }

    public ClientInvocationException(String message) {
        super(message);
    }
}
