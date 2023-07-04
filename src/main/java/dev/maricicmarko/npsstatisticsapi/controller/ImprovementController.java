package dev.maricicmarko.npsstatisticsapi.controller;

import dev.maricicmarko.npsstatisticsapi.model.Improvement;
import dev.maricicmarko.npsstatisticsapi.repo.ImprovementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ImprovementController {

    @Autowired
    private ImprovementRepo improvementRepo;


    @GetMapping("getAllImprovements")
    public ResponseEntity<List<Improvement>> getAllImprovement(){

        try {

            List<Improvement> improvementList = new ArrayList<>(improvementRepo.findAll());

            if(improvementList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {

                return new ResponseEntity<>(improvementList, HttpStatus.OK);

            }

        }catch (Exception ex){

            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
