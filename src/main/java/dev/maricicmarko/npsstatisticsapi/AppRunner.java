package dev.maricicmarko.npsstatisticsapi;

import dev.maricicmarko.npsstatisticsapi.model.QuarterYear;
import dev.maricicmarko.npsstatisticsapi.model.Question;
import dev.maricicmarko.npsstatisticsapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final QuarterlyNpsService quarterlyNpsService;
    private final CountryQuarterlyService countryQuarterlyService;
    private final QuarterYearService quarterYearService;
    private final CountryService countryService;
    private final ImprovementService improvementService;
    private final QuestionService questionService;

    @Autowired
    public AppRunner(QuarterlyNpsService quarterlyNpsService, CountryQuarterlyService countryQuarterlyService
            , QuarterYearService quarterYearService, CountryService countryService
            , ImprovementService improvementService, QuestionService questionService){

        this.quarterlyNpsService = quarterlyNpsService;
        this.countryQuarterlyService = countryQuarterlyService;
        this.quarterYearService = quarterYearService;
        this.countryService = countryService;
        this.improvementService = improvementService;
        this.questionService = questionService;

    }

    @Override
    public void run(String... args) throws Exception{

        quarterlyNpsService.calculateAndSaveQuarterlyNps();
        countryQuarterlyService.calculateAndSaveCountryQuarterlyNps();
        quarterYearService.generateQuarterYearInitial();
        countryService.extractAndSaveDistinctCountries();
        improvementService.calculateAndSaveImprovementQuestions();
        questionService.saveDistinctQuestions();

    }
}
