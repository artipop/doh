package io.deffun.doh;

public final class ClientTimeoutException extends RuntimeException {
    public ClientTimeoutException(Throwable cause) {
        super(cause);
    }
}
