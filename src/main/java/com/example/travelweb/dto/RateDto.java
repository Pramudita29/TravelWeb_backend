package com.example.travelweb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class RateDto {

    private Long id;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer uploadId;

    @NotNull
    private Integer rate;
}
