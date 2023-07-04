package dev.maricicmarko.npsstatisticsapi.service;

import dev.maricicmarko.npsstatisticsapi.model.NpsSurvey;
import dev.maricicmarko.npsstatisticsapi.model.QuarterlyNps;
import dev.maricicmarko.npsstatisticsapi.repo.NpsSurveyRepo;
import dev.maricicmarko.npsstatisticsapi.repo.QuarterlyNpsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuarterlyNpsService {

    private final NpsSurveyRepo npsSurveyRepo;

    private final QuarterlyNpsRepo quarteryNpsRepo;

    @Autowired
    public QuarterlyNpsService(NpsSurveyRepo npsSurveyRepo, QuarterlyNpsRepo quarteryNpsRepo ) {

        this.npsSurveyRepo = npsSurveyRepo;

        this.quarteryNpsRepo = quarteryNpsRepo;

    }

    @Transactional
    public void calculateAndSaveQuarterlyNps() {

        try {
            // Retrieve the data
            List<NpsSurvey> npsSurveys = npsSurveyRepo.findAll();

            // Group surveys by quarter-year based on answer_date
            Map<String, List<NpsSurvey>> npsSurveyByQuarterYear = npsSurveys.stream()
                    .collect(Collectors.groupingBy(this::getQuarterYear));

            // Calculate NPS score and percentages for each quarter-year
            List<QuarterlyNps> quarterlyNpsList = new ArrayList<>();

            for (Map.Entry<String, List<NpsSurvey>> entry : npsSurveyByQuarterYear.entrySet()) {
                String quarterYear = entry.getKey();
                List<NpsSurvey> quarterYearNpsSurveys = entry.getValue();

                // Calculate NPS score and percentages for the current quarter-year
                int totalResponses = quarterYearNpsSurveys.size();
                long detractorsCount = quarterYearNpsSurveys.stream()
                        .filter(npsSurvey -> npsSurvey.getRating() >= 0 && npsSurvey.getRating() <= 6)
                        .count();
                long passivesCount = quarterYearNpsSurveys.stream()
                        .filter(npsSurvey -> npsSurvey.getRating() >= 7 && npsSurvey.getRating() <= 8)
                        .count();
                long promotersCount = quarterYearNpsSurveys.stream()
                        .filter(npsSurvey -> npsSurvey.getRating() >= 9 && npsSurvey.getRating() <= 10)
                        .count();

                float detractorsPercentage = (float) detractorsCount / totalResponses * 100;
                float passivesPercentage = (float) passivesCount / totalResponses * 100;
                float promotersPercentage = (float) promotersCount / totalResponses * 100;

                int npsScore = Math.round(promotersPercentage - detractorsPercentage);

                // Create a QuarterlyNps instance for the current quarter-year
                QuarterlyNps quarterlyNps = new QuarterlyNps();
                quarterlyNps.setQuarterYear(quarterYear);
                quarterlyNps.setDetractors(detractorsPercentage);
                quarterlyNps.setPassives(passivesPercentage);
                quarterlyNps.setPromoters(promotersPercentage);
                quarterlyNps.setScore(npsScore);
                quarterlyNps.setResponses(totalResponses);

                quarterlyNpsList.add(quarterlyNps);
            }

            // Save the QuarterlyNps instances to the database table
            quarteryNpsRepo.saveAll(quarterlyNpsList);

        }catch (Exception ex){

            ex.printStackTrace();

        }
    }

    private String getQuarterYear(NpsSurvey npsSurvey) {
        int year = npsSurvey.getAnswerDate().getYear();
        int month = npsSurvey.getAnswerDate().getMonthValue();
        int quarter = (month - 1) / 3 + 1;
        return "Q" + quarter + " " + year;
    }


}
