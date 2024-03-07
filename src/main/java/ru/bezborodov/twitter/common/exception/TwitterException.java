package ru.bezborodov.twitter.common.exception;

public class TwitterException extends RuntimeException {
    public TwitterException(String message) {
        super(message);
    }
}
