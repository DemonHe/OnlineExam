package analyse.repository;

import analyse.domain.entity.MarkList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Kimone.
 *
 * 生成成绩单
 */

public interface ReportCardsRepository extends JpaRepository<MarkList, Integer> {
    public List<MarkList> findByTestID(int testID);

    public int countByTestID(int testID);

    @Query(nativeQuery=true,value = "select * from mark_list where testID=:testID limit :start,:len ")
    public List<MarkList> findForTable(@Param("testID") int testID, @Param("start") int start, @Param("len") int length);

    public MarkList findByTestIDAndSid(int testID, String sid);
}
