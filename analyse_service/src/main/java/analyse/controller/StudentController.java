package analyse.controller;

import analyse.domain.entity.Student;
import analyse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kimone.
 */
@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/getStudent")
    public Student getStudent(String sid) {
        return studentRepository.findBySid(sid);
    }
}
