package dev.maricicmarko.npsstatisticsapi.service;

import dev.maricicmarko.npsstatisticsapi.model.QuarterYear;
import dev.maricicmarko.npsstatisticsapi.repo.QuarterYearRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuarterYearService {

    private final QuarterYearRepo quarterYearRepo;

    @Autowired
    public QuarterYearService(QuarterYearRepo quarterYearRepo){

        this.quarterYearRepo = quarterYearRepo;

    }

    @Transactional
    public void createQuarterYearIfNeeded(){

        try {
            String currentQuarter = getQuarterFromCurrentDate();
            QuarterYear existingQuarterYear = quarterYearRepo.findByQuarterYear(currentQuarter);

            if (existingQuarterYear == null) {
                QuarterYear newQuarterYear = new QuarterYear();
                newQuarterYear.setQuarterYear(currentQuarter);
                quarterYearRepo.save(newQuarterYear);
            }
        }catch (Exception ex){

            ex.printStackTrace();

        }
    }
    private String getQuarterFromCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        int yearMonth = currentDate.getMonthValue();
        int quarter = (yearMonth - 1) / 3 + 1;
        int year = currentDate.getYear();

        return "Q" + quarter + " " + year;
    }

    @Transactional
    public void generateQuarterYearInitial() {

        try {
            List<QuarterYear> quarterYearList = new ArrayList<>();
            LocalDate fromDate = LocalDate.now().withYear(2021).withMonth(1);
            LocalDate toDate = LocalDate.now();

            // Generate quarterly labels for the past three years
            for (int i = fromDate.getYear(); i <= toDate.getYear(); i++) {
                if (i != toDate.getYear()) {
                    for (int quarter = 1; quarter <= 4; quarter++) {
                        String quarterLabel = "Q" + quarter + " " + i;
                        QuarterYear quarterYear = new QuarterYear();
                        quarterYear.setQuarterYear(quarterLabel);
                        quarterYearList.add(quarterYear);
                    }
                } else {
                    int month = toDate.getMonthValue();
                    int quarter = (month - 1) / 3 + 1;
                    for (int j = 1; j <= quarter; j++) {

                        String quarterLabel = "Q" + j + " " + toDate.getYear();

                        QuarterYear quarterYear = new QuarterYear();
                        quarterYear.setQuarterYear(quarterLabel);
                        quarterYearList.add(quarterYear);

                    }
                }
            }

            quarterYearRepo.saveAll(quarterYearList);

        }catch (Exception ex){

            ex.printStackTrace();

        }
    }

}
