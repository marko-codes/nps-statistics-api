package dev.maricicmarko.npsstatisticsapi.repo;

import dev.maricicmarko.npsstatisticsapi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {

    boolean existsByCountryName(String countryName);

}
