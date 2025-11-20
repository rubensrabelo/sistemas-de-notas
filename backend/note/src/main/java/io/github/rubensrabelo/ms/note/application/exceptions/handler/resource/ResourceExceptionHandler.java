package io.github.rubensrabelo.ms.note.application.exceptions.handler.resource;

import io.github.rubensrabelo.ms.note.application.exceptions.domain.ResourceNotFoundException;
import io.github.rubensrabelo.ms.note.application.exceptions.dto.StandardError;
import io.github.rubensrabelo.ms.note.application.exceptions.handler.base.ErrorBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    private final ErrorBuilder errorBuilder;

    public ResourceExceptionHandler(ErrorBuilder errorBuilder) {
        this.errorBuilder = errorBuilder;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        return errorBuilder.build(status, error, e.getMessage(), request);
    }
}
