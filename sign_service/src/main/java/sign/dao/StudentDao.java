package sign.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sign.model.Student;

import java.util.List;

/**
 * Created by sam.
 */
@Repository
public interface StudentDao extends JpaRepository<Student,String> {

    //注册
    public Student save(Student student);

    //验证sid与email的唯一性 在注册中
    @Query("select s.name from Student s where s.sid = ?1")
    public String checkSid(String sid);

    @Query("select s.name from Student s where s.email = ?1")
    public String checkEmail(String email);

    @Query("select s.sid from Student s where s.code = ?1")
    public String findbyCode(String code);

    @Transactional
    @Modifying
    @Query("update Student s set s.state = ?2 where s.sid = ?1")
    public void updateState(String sid,String state);

    /*----------------------------------------------------------*/
    //登录
    @Query("select s.password from Student s where s.email = ?1")
    public String findbyEmail(String email);

    @Query("select s.password from Student s where s.sid = ?1")
    public String findbySid(String sid);

    @Query("select s.sid from Student s where s.email = ?1")
    public String findSidByEmail(String email);

    @Query("select s.name from Student s where s.email = ?1")
    public String findNameByEmail(String email);

    @Query("select s.name from Student s where s.sid = ?1")
    public String findNameBySid(String sid);

    @Query("select s.state from Student s where s.email = ?1")
    public String checkStateUseEmail(String email);

    @Query("select s.state from Student s where s.sid = ?1")
    public String checkStateUseSid(String sid);

    public Student getOne(String sid);
}
