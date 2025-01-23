package com.test_task.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TeamNotFoundException extends ResponseStatusException {
    public TeamNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
