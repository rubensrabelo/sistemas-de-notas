package io.github.rubensrabelo.ms.note.application.exceptions.domain;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
