package dev.maricicmarko.npsstatisticsapi.controller;

import dev.maricicmarko.npsstatisticsapi.model.QuarterYear;
import dev.maricicmarko.npsstatisticsapi.repo.QuarterYearRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuarterYearController {

    @Autowired
    private QuarterYearRepo quarterYearRepo;


    @GetMapping("/getAllQuarterYear")
    public ResponseEntity<List<QuarterYear>> getAllQuarterYear(){

        try {

            List<QuarterYear> quarterYearList = new ArrayList<>(quarterYearRepo.findAll());

            if(quarterYearList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {

                return new ResponseEntity<>(quarterYearList, HttpStatus.OK);

            }

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
