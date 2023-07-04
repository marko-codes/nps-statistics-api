package dev.maricicmarko.npsstatisticsapi.schedule;

import dev.maricicmarko.npsstatisticsapi.service.QuarterYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuarterYearScheduler {

    @Autowired
    private final QuarterYearService quarterYearService;

    public QuarterYearScheduler(QuarterYearService quarterYearService){
        this.quarterYearService = quarterYearService;
    }

    // Runs on the 1st day of January, April, July, and October at midnight
    @Scheduled(cron = "0 0 0 1 1,4,7,10 *")
    public void generateQuarterYear() {
        quarterYearService.createQuarterYearIfNeeded();
    }

    //Here we can add other cron jobs for calculations of the quarters that have been passed

}
