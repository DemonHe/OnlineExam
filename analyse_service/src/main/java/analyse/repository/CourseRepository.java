package analyse.repository;

import analyse.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {
    public Course findById(int id);
}
