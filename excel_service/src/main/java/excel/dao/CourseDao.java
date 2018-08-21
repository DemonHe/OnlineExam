package excel.dao;

import excel.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {

    public List<Course> findByName(String name);

    @Query("select c.id from Course c where c.name = ?1")
    public int findIDByName(String name);
}
