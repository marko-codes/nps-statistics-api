package dev.maricicmarko.npsstatisticsapi.repo;

import dev.maricicmarko.npsstatisticsapi.model.QuarterYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuarterYearRepo extends JpaRepository<QuarterYear, Integer> {
    QuarterYear findByQuarterYear(String quarter);
}
