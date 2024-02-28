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
@Table(name = "upload")
public class UploadEntity {

    @Id
    @SequenceGenerator(name = "upload_seq_gen", sequenceName = "upload_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "upload_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private String duration;

    @Column(name = "min_pax")
    private Integer minPax;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "destination")
    private String destination;

    @Column(name = "description", length = 1000000)
    private String description;

    @Column(name = "province")
    private String province;

    @Column(name = "district")
    private String district;

    @Column(name = "region")
    private String region;

    @Column(name = "price")
    private String price;

    @Column(name="category")
    private String category;

}
