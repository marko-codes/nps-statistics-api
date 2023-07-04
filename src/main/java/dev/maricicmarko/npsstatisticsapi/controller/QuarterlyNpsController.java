package dev.maricicmarko.npsstatisticsapi.controller;

import dev.maricicmarko.npsstatisticsapi.model.QuarterlyNps;
import dev.maricicmarko.npsstatisticsapi.repo.QuarterlyNpsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuarterlyNpsController {

    @Autowired
    private QuarterlyNpsRepo quarterlyNpsRepo;

    @GetMapping("/getAllQuarterlyNps")
    public ResponseEntity<List<QuarterlyNps>> getAllQuarterlyNps(){

        try {

            List<QuarterlyNps> quarterlyNpsList = new ArrayList<>(quarterlyNpsRepo.findAll());

            if(quarterlyNpsList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {

                return new ResponseEntity<>(quarterlyNpsList, HttpStatus.OK);

            }

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
