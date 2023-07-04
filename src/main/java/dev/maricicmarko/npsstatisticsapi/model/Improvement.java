package dev.maricicmarko.npsstatisticsapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Improvement")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Improvement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    @Column(name = "quarter")
    private String quarterYear;

    private Integer percentage;
}
