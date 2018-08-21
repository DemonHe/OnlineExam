package attribute.dao;

import attribute.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    @Query("select count(q.id) from Question q where q.courseID = ?1 and q.score = ?2")
    public int findByCourseIDAndScore(int cid, double score);
}
