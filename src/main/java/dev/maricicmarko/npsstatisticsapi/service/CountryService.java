package dev.maricicmarko.npsstatisticsapi.service;

import dev.maricicmarko.npsstatisticsapi.model.Country;
import dev.maricicmarko.npsstatisticsapi.repo.CountryRepo;
import dev.maricicmarko.npsstatisticsapi.repo.NpsSurveyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepo countryRepo;
    private final NpsSurveyRepo npsSurveyRepo;

    @Autowired
    public CountryService(CountryRepo countryRepo, NpsSurveyRepo npsSurveyRepo){

        this.countryRepo = countryRepo;
        this.npsSurveyRepo = npsSurveyRepo;

    }

    @Transactional
    public void extractAndSaveDistinctCountries() {

        try {
            List<Country> countries = new ArrayList<>();
            List<String> distinctCountries = npsSurveyRepo.findDistinctCountries();

            for (String countryName : distinctCountries) {
                if (!countryRepo.existsByCountryName(countryName)) {

                    Country country = new Country();
                    country.setCountryName(countryName);
                    countries.add(country);
                }
            }
            countryRepo.saveAll(countries);
        }catch (Exception ex){

            ex.printStackTrace();

        }

    }

}
