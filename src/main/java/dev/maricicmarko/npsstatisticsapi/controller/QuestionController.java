package dev.maricicmarko.npsstatisticsapi.controller;

import dev.maricicmarko.npsstatisticsapi.model.Question;
import dev.maricicmarko.npsstatisticsapi.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepo questionRepo;


    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        try {

            List<Question> questionList = new ArrayList<>(questionRepo.findAll());

            if(questionList.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }else {

                return new ResponseEntity<>(questionList, HttpStatus.OK);

            }

        }catch (Exception ex){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
