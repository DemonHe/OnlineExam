package course.dao;

import course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {

    public List<Course> findAll();

    public Course findById(int id);

    public Course findByName(String name);

    @Query(value = "select * from Course c where c.name like %?1%",nativeQuery=true)
    public List<Course> findByNameLike(String name);

    public void delete(int id);

    public Course save(Course course);
}
