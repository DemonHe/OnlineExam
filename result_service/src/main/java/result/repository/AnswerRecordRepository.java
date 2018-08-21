package result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import result.domain.AnswerRecord;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Kimone.
 */
public interface AnswerRecordRepository extends JpaRepository<AnswerRecord, Integer> {
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update AnswerRecord set isSelected=:isSelected where id=:id")
    public int setIsSelected(@Param("isSelected") int isSelected, @Param("id") int id);

    public List<AnswerRecord> findByTestIDAndSidAndQid(int testID, String sid, int qid);
}
