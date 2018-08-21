package result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import result.domain.MarkList;

/**
 * Created by Kimone.
 */
public interface MarkListRepository extends JpaRepository<MarkList, Integer> {
    @Query("select m.mark from MarkList m where m.sid=:sid and m.testID=:testID")
    public double getMark(@Param("sid") String sid, @Param("testID") int testID);

    @Transactional
    @Modifying
    @Query("update MarkList set mark=:mark, isSubmit=1 where testID=:testID and sid=:sid")
    public int setMark(@Param("mark") double mark, @Param("testID") int testID, @Param("sid") String sid);
}
