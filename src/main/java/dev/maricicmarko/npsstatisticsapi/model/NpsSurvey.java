package dev.maricicmarko.npsstatisticsapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Survey")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class NpsSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    private LocalDate startDate;

    private LocalDate answerDate;

    private Integer rating;

    private String improvement;

}
