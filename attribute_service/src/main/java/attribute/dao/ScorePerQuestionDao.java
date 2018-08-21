package attribute.dao;

import attribute.model.ScorePerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScorePerQuestionDao extends JpaRepository<ScorePerQuestion,Integer> {

    public ScorePerQuestion save(ScorePerQuestion scorePerQuestion);
}
