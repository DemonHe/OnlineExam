package analyse.repository;

import analyse.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kimone.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public Question findById(int id);
}
