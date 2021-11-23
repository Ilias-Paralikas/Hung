package Exceptions;

public class UnbalancedException extends Exception {
    public UnbalancedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}