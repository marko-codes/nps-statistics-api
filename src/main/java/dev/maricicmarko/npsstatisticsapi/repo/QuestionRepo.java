package dev.maricicmarko.npsstatisticsapi.repo;

import dev.maricicmarko.npsstatisticsapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    boolean existsByQuestion(String question);

}
