package analyse.repository;

import analyse.domain.entity.AnswerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface AnswerRecordRepository extends JpaRepository<AnswerRecord, Integer> {
    String findSelectionSql = "select s.content from Selection s, AnswerRecord ar where s.id=ar.selectionID";

    @Query("select s.content from Selection s, AnswerRecord ar where ar.testID=:testID and ar.sid=:sid and ar.qid=:qid" +
            " and s.id=ar.selectionID order by ar.optOrder asc ")
    public List<String> findSelection(@Param("testID") int testID, @Param("sid") String sid, @Param("qid") int qid);

    @Query("select a.optOrder from AnswerRecord a where a.testID=:testID and a.sid=:sid and a.qid=:qid and a.isSelected=1 order by a.optOrder asc")
    public List<String> findAnswers(@Param("testID") int testID, @Param("sid") String sid, @Param("qid") int qid);
}
