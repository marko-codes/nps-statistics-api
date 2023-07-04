package dev.maricicmarko.npsstatisticsapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Countryq")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CountryQuarterly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    @Column(name = "quarter")
    private String quarterYear;

    private Float detractors;

    private Float passives;

    private Float promoters;

    private Integer score;

    private Integer responses;
}
