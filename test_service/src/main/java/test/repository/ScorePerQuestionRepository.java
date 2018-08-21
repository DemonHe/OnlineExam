package test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import test.domain.ScorePerQuestion;

public interface ScorePerQuestionRepository extends JpaRepository<ScorePerQuestion, Integer>{

	public List<ScorePerQuestion> findByTestID(int id);
}
