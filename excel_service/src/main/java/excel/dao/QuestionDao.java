package excel.dao;

import excel.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    public Question save(Question question);
}
