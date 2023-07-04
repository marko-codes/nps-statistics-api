package dev.maricicmarko.npsstatisticsapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Quarter")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuarterYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quarter")
    private String quarterYear;

}
