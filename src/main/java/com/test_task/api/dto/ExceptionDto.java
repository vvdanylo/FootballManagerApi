package com.test_task.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@Builder
@AllArgsConstructor
public class ExceptionDto {
    private HttpStatusCode statusCode;
    private String message;
}