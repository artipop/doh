package io.deffun.doh;

public final class ClientConfigurationException extends RuntimeException {
    public ClientConfigurationException(Throwable cause) {
        super(cause);
    }

    public ClientConfigurationException(String message) {
        super(message);
    }
}
