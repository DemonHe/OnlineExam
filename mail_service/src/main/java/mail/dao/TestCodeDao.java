package mail.dao;

import mail.model.TestCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by sam.
 */

@Repository
public interface TestCodeDao extends JpaRepository<TestCode,String> {

    //保存考试码
    public TestCode save(TestCode code);

    @Query("select t.code from TestCode t where t.tid = ?1")
    public String findCodeByTid(String tid);

}
