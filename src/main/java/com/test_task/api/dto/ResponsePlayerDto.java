package com.test_task.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePlayerDto {
    private Long id;

    private String name;

    private String surname;

    private Integer age;

    private Integer experienceInMonths;

    private String teamName;
}
