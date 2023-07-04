package dev.maricicmarko.npsstatisticsapi.repo;

import dev.maricicmarko.npsstatisticsapi.model.CountryQuarterly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryQuarterlyRepo extends JpaRepository<CountryQuarterly, Integer> {
}
