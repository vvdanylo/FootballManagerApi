package com.test_task.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ValidationExceptionDto {
    private HttpStatusCode statusCode;
    private List<String> errors;
}
