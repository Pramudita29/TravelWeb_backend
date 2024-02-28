package com.example.travelweb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadDTO {

    private Integer id;

    @NotNull
    private String image;

    @NotNull
    private String title;

    @NotNull
    private String duration;

    @NotNull
    private Integer minPax;

    @NotNull
    private String difficulty;

    @NotNull
    private String destination;

    @NotNull
    private String description;

    @NotNull
    private String province;

    @NotNull
    private String district;

    @NotNull
    private String region;

    @NotNull
    private String price;

    @NotNull
    private String category;

}
