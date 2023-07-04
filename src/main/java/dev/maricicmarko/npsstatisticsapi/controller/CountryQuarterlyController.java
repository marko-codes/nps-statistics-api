package dev.maricicmarko.npsstatisticsapi.controller;

import dev.maricicmarko.npsstatisticsapi.model.CountryQuarterly;
import dev.maricicmarko.npsstatisticsapi.repo.CountryQuarterlyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryQuarterlyController {

    @Autowired
    private CountryQuarterlyRepo countryQuarterlyRepo;


    @GetMapping("/getAllCountryQuarterly")
    public ResponseEntity<List<CountryQuarterly>> getAllCountryQuarterly(){

        try {

            List<CountryQuarterly> countryQuarterlyList = new ArrayList<>(countryQuarterlyRepo.findAll());

            if(countryQuarterlyList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {

                return new ResponseEntity<>(countryQuarterlyList, HttpStatus.OK);

            }

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
