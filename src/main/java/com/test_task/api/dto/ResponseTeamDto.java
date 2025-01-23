package com.test_task.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTeamDto {
    private Long id;

    private String name;

    private Double transferCommission;

    private BigDecimal balance;

    private List<ResponsePlayerDto> players;
}
