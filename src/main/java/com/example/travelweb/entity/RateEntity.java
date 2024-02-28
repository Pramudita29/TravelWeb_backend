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
@Table(name = "rate")
public class RateEntity {
    @Id
    @SequenceGenerator(name = "upload_seq_gen", sequenceName = "upload_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "upload_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_id", referencedColumnName = "id")
    private UploadEntity upload;


    @Column(name = "rate", nullable = false)
    private Integer rate;
}
