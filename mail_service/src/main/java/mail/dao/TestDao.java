package mail.dao;

import mail.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by sam on 2017/12/16.
 */
public interface TestDao extends JpaRepository<Test,Integer> {

    @Query("select t.courseID from Test t where t.id = ?1")
    public int getCouseId(int tid);
}
