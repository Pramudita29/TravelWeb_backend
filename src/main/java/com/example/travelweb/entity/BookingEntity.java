package com.example.travelweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @SequenceGenerator(name = "booking_seq_gen", sequenceName = "booking_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "booking_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "tour_date")
    private String tourDate;

    @Column(name = "tour_name")
    private String tourName;

    @Column(name = "num_persons")
    private Integer numPersons;

    @Column(name = "total_price")
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Temporarily allow nulls
    private UserEntity user;

}
