package com.example.travelweb.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BookingDTO {

    private Integer id;

    private String fullName;

    private String email;

    private String phone;

    private String tourDate;

    private String tourName;

    private Integer numPersons;

    private Integer totalPrice;

    private Integer userId;

}
