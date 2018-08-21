package mail.dao;

import mail.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by sam.
 */
@Repository
public interface StudentMailDao extends JpaRepository<Student,String> {

    public Student getOne(String sid);

    @Query("select s.email from Student s where s.sid = ?1")
    public String getEmailBySid(String sid);
}
