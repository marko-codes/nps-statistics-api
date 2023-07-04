package dev.maricicmarko.npsstatisticsapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Quarterly")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuarterlyNps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="quarter")
    private String quarterYear;

    private Integer score;

    private Integer responses;

    private Float detractors;

    private Float passives;

    private Float promoters;

}
