package attribute.dao;

import attribute.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {

    public List<Course> findByName(String name);

    public List<Course> findAll();

    @Query("select c.id from Course c where c.name = ?1")
    public int findIDByName(String name);

    @Query("select c.name from Course c")
    public List<String> findAllNames();
}
