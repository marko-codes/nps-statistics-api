package dev.maricicmarko.npsstatisticsapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Question")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

}
