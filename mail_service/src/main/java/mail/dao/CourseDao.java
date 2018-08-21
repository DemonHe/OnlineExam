package mail.dao;

import mail.model.Course;
import mail.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by sam on 2017/12/16.
 */
public interface CourseDao extends JpaRepository<Course,Integer> {

    @Query("select c.name from Course c where c.id = ?1")
    public String getName(int courseId);
}
