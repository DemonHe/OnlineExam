package excel.dao;

import excel.model.StudentList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentListDao extends JpaRepository<StudentList,Integer> {

    public StudentList save(StudentList studentList);
}
