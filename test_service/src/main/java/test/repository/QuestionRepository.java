package test.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import test.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	public ArrayList<Question> findByScoreAndCourseID(double score, int cid);

	public Question findById(int qid);
}
