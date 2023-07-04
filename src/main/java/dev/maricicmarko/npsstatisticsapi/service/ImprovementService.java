package dev.maricicmarko.npsstatisticsapi.service;

import dev.maricicmarko.npsstatisticsapi.model.Improvement;
import dev.maricicmarko.npsstatisticsapi.model.NpsSurvey;
import dev.maricicmarko.npsstatisticsapi.repo.ImprovementRepo;
import dev.maricicmarko.npsstatisticsapi.repo.NpsSurveyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImprovementService {

    private final ImprovementRepo improvementRepo;
    private final NpsSurveyRepo npsSurveyRepo;

    @Autowired
    public ImprovementService(ImprovementRepo improvementRepo, NpsSurveyRepo npsSurveyRepo){

        this.improvementRepo = improvementRepo;
        this.npsSurveyRepo = npsSurveyRepo;

    }

    @Transactional
    public void calculateAndSaveImprovementQuestions(){

        try {
            List<Improvement> improvements = new ArrayList<>();

            List<NpsSurvey> npsSurveys = npsSurveyRepo.findAll();

            //Count the Improvements by quarter-year
            Map<String, Long> totalImprovementsByQuarter = npsSurveys.stream()
                    .collect(Collectors.groupingBy(this::getQuarterYear, Collectors.counting()));

            //group by question and quarter-year
            Map<String, Map<String, List<NpsSurvey>>> groupedByQuestionAndQuarterYear = npsSurveys.stream()
                    .collect(Collectors.groupingBy(this::getQuarterYear
                            , Collectors.groupingBy(NpsSurvey::getImprovement)));

            for (Map.Entry<String, Map<String, List<NpsSurvey>>> quarterYearEntry : groupedByQuestionAndQuarterYear.entrySet()) {
                String quarterYear = quarterYearEntry.getKey();
                Map<String, List<NpsSurvey>> groupedByQuarter = quarterYearEntry.getValue();

                for (Map.Entry<String, List<NpsSurvey>> improvementEntry : groupedByQuarter.entrySet()) {
                    String question = improvementEntry.getKey();
                    List<NpsSurvey> npsSurveyList = improvementEntry.getValue();

                    //Count total and calculate percentage
                    int totalCount = npsSurveyList.size();

                    int questionPercentage = Math.round((float) totalCount / totalImprovementsByQuarter.get(quarterYear) * 100);

                    //Add to a list of Improvements
                    Improvement improvement = new Improvement();
                    improvement.setQuestion(question);
                    improvement.setPercentage(questionPercentage);
                    improvement.setQuarterYear(quarterYear);
                    improvements.add(improvement);
                }
            }

            //Save to improvements table
            improvementRepo.saveAll(improvements);

        }catch (Exception ex){

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
