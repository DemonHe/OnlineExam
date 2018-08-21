package excel.dao;

import excel.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {

    public Student findByEmail(String email);
}
