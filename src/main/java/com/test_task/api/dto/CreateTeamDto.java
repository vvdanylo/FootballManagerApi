package com.test_task.api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamDto {
    @NotNull(message = "Team's name cannot be null")
    @NotBlank(message = "Team's name must contain at least one non-whitespace character")
    @Size(min = 1, max = 32, message = "Team's name must be between 1 and 32 characters")
    private String name;

    @NotNull(message = "Team's transfer commission cannot be null")
    @DecimalMin(value = "0.00", message = "Team's transfer commission must be non-negative")
    @DecimalMax(value = "0.10", message = "Team's transfer commission must not exceed 0.10 (10%)")
    private Double transferCommission;

    @NotNull(message = "Team's balance cannot be null")
    @DecimalMin(value = "0.00", message = "Team's balance must be non-negative")
    private BigDecimal balance;
}