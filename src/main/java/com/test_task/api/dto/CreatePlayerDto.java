package com.test_task.api.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerDto {
    @NotNull(message = "Player's name cannot be null")
    @NotBlank(message = "Player's name must contain at least one non-whitespace character")
    @Size(min = 1, max = 32, message = "Player's name must be between 1 and 32 characters")
    private String name;

    @NotNull(message = "Player's surname cannot be null")
    @NotBlank(message = "Player's surname must contain at least one non-whitespace character")
    @Size(min = 1, max = 32, message = "Player's surname must be between 1 and 32 characters")
    private String surname;

    @NotNull(message = "Player's age cannot be null")
    @Min(value = 18, message = "Player's age must be at least 18 y.o.")
    @Max(value = 100, message = "Player's age must be no more than 100 y.o.")
    private Integer age;

    @NotNull(message = "Player's experience cannot be null")
    @Min(value = 0, message = "Player's experience must be non-negative")
    private Integer experienceInMonths;
}