package dev.maricicmarko.npsstatisticsapi.controller;

import dev.maricicmarko.npsstatisticsapi.model.NpsSurvey;
import dev.maricicmarko.npsstatisticsapi.repo.NpsSurveyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NpsSurveyController {

    @Autowired
    private NpsSurveyRepo npsSurveyRepo;

    @GetMapping("/getAllNpsSurveys")
    public ResponseEntity<List<NpsSurvey>> getAllNpsSurveys() {

        try {

            List<NpsSurvey> npsSurveyList = new ArrayList<>(npsSurveyRepo.findAll());

            if(npsSurveyList.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {

                return new ResponseEntity<>(npsSurveyList, HttpStatus.OK);

            }

        } catch (Exception ex) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getNpsSurveysById/{id}")
    public ResponseEntity<NpsSurvey> getNpsSurveyById(@PathVariable Integer id) {

        Optional<NpsSurvey> npsSurveyData = npsSurveyRepo.findById(id);

        if(npsSurveyData.isEmpty()){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }else {

            return new ResponseEntity<>(npsSurveyData.get(), HttpStatus.OK);

        }

    }

}
