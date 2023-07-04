package dev.maricicmarko.npsstatisticsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NpsStatisticsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NpsStatisticsApiApplication.class, args);
	}

}
