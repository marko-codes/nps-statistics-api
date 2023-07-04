package dev.maricicmarko.npsstatisticsapi.repo;

import dev.maricicmarko.npsstatisticsapi.model.Improvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImprovementRepo extends JpaRepository<Improvement, Integer> {

    boolean existsByQuestion(String question);

}
