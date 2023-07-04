package dev.maricicmarko.npsstatisticsapi.repo;

import dev.maricicmarko.npsstatisticsapi.model.NpsSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NpsSurveyRepo extends JpaRepository<NpsSurvey, Integer> {

    @Query("SELECT DISTINCT s.country FROM NpsSurvey s")
    List<String> findDistinctCountries();

    @Query("SELECT DISTINCT s.improvement FROM NpsSurvey s")
    List<String> findDistinctImprovements();

}
