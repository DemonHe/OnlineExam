package analyse.repository;

import analyse.domain.entity.MarkPerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kimone.
 *
 * 考试结束生成试卷
 */
public interface TestPaperRepository extends JpaRepository<MarkPerQuestion, Integer> {
    public List<MarkPerQuestion> findByTestIDAndSid(int testID, String sid);
}
