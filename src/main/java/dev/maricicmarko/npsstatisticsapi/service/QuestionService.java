package dev.maricicmarko.npsstatisticsapi.service;

import dev.maricicmarko.npsstatisticsapi.model.NpsSurvey;
import dev.maricicmarko.npsstatisticsapi.model.Question;
import dev.maricicmarko.npsstatisticsapi.repo.NpsSurveyRepo;
import dev.maricicmarko.npsstatisticsapi.repo.QuarterYearRepo;
import dev.maricicmarko.npsstatisticsapi.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    private final NpsSurveyRepo npsSurveyRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo, NpsSurveyRepo npsSurveyRepo){

        this.npsSurveyRepo = npsSurveyRepo;
        this.questionRepo = questionRepo;
    }

    @Transactional
    public void saveDistinctQuestions(){

        try {
            List<Question> questions = new ArrayList<>();
            List<String> improvements = npsSurveyRepo.findDistinctImprovements();

            for (String improvement : improvements) {
                if (!questionRepo.existsByQuestion(improvement)) {

                    Question question = new Question();
                    question.setQuestion(improvement);
                    questions.add(question);

                }
            }
            questionRepo.saveAll(questions);

        }catch (Exception ex){

            ex.printStackTrace();

        }
    }

}
