package com.test_task.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransferPlayerException extends ResponseStatusException {
    public TransferPlayerException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
