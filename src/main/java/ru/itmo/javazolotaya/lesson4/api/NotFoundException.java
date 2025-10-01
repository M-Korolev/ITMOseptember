package ru.itmo.javazolotaya.lesson4.api;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}