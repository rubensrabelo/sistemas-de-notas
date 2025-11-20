package io.github.rubensrabelo.ms.note.application.exceptions.domain;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
