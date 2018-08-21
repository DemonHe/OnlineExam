package analyse.repository;

import analyse.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kimone.
 */
public interface StudentRepository extends JpaRepository<Student, String> {
    public Student findBySid(String sid);
}
