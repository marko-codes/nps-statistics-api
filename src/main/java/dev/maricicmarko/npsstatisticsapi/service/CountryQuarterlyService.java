package dev.maricicmarko.npsstatisticsapi.service;

import dev.maricicmarko.npsstatisticsapi.model.CountryQuarterly;
import dev.maricicmarko.npsstatisticsapi.model.NpsSurvey;
import dev.maricicmarko.npsstatisticsapi.repo.CountryQuarterlyRepo;
import dev.maricicmarko.npsstatisticsapi.repo.NpsSurveyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryQuarterlyService {

    private final NpsSurveyRepo npsSurveyRepo;
    private final CountryQuarterlyRepo countryQuarterlyRepo;

    @Autowired
    public CountryQuarterlyService(NpsSurveyRepo npsSurveyRepo, CountryQuarterlyRepo countryQuarterlyRepo){

        this.npsSurveyRepo = npsSurveyRepo;
        this.countryQuarterlyRepo = countryQuarterlyRepo;

    }

    @Transactional
    public void calculateAndSaveCountryQuarterlyNps(){

        try {
            //Retrieve the data
            List<NpsSurvey> npsSurveys = npsSurveyRepo.findAll();

            //Group surveys by country and quarter-year based on answer_date
            Map<String, Map<String, List<NpsSurvey>>> npsSurveyByCountryQuarterYear = npsSurveys.stream()
                    .collect(Collectors.groupingBy(NpsSurvey::getCountry,
                            Collectors.groupingBy(this::getQuarterYear)));

            //Calculate NPS score and percentages for each country and quarter-year
            List<CountryQuarterly> countryQuarterlyList = new ArrayList<>();

            for (Map.Entry<String, Map<String, List<NpsSurvey>>> countryEntry : npsSurveyByCountryQuarterYear.entrySet()) {
                String country = countryEntry.getKey();
                Map<String, List<NpsSurvey>> quarterYearMap = countryEntry.getValue();

                for (Map.Entry<String, List<NpsSurvey>> entry : quarterYearMap.entrySet()) {
                    String quarterYear = entry.getKey();
                    List<NpsSurvey> quarterYearNpsSurveys = entry.getValue();

                    //Calculate NPS score and percentages for the current quarter-year
                    int totalResponses = quarterYearNpsSurveys.size();
                    long detractorsCount = quarterYearNpsSurveys.stream()
                            .filter(npsSurvey -> npsSurvey.getRating() >= 0 && npsSurvey.getRating() <= 6).count();
                    long passivesCount = quarterYearNpsSurveys.stream()
                            .filter(npsSurvey -> npsSurvey.getRating() >= 7 && npsSurvey.getRating() <= 8).count();
                    long promotersCount = quarterYearNpsSurveys.stream()
                            .filter(npsSurvey -> npsSurvey.getRating() >= 9 && npsSurvey.getRating() <= 10).count();

                    float detractorsPercentage = (float) detractorsCount / totalResponses * 100;
                    float passivesPercentage = (float) passivesCount / totalResponses * 100;
                    float promotersPercentage = (float) promotersCount / totalResponses * 100;

                    int npsScore = Math.round(promotersPercentage - detractorsPercentage);

                    //Create a CountryQuarterly instance for the current country and quarter-year
                    CountryQuarterly countryQuarterly = new CountryQuarterly();
                    countryQuarterly.setCountry(country);
                    countryQuarterly.setPromoters(promotersPercentage);
                    countryQuarterly.setDetractors(detractorsPercentage);
                    countryQuarterly.setPassives(passivesPercentage);
                    countryQuarterly.setScore(npsScore);
                    countryQuarterly.setQuarterYear(quarterYear);
                    countryQuarterly.setResponses(totalResponses);

                    countryQuarterlyList.add(countryQuarterly);

                }
            }
            //Save the CountryQuarterly instances to the database table
            countryQuarterlyRepo.saveAll(countryQuarterlyList);

        }catch(Exception ex){

            ex.printStackTrace();

        }


    }

    private String getQuarterYear(NpsSurvey npsSurvey){

        int year = npsSurvey.getAnswerDate().getYear();
        int month = npsSurvey.getAnswerDate().getMonthValue();
        int quarter = (month - 1) / 3 + 1;

        return "Q" + quarter + " " + year;

    }

}
