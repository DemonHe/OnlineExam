package result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import result.domain.Question;

/**
 * Created by Kimone.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    public Question findById(int id);
}
