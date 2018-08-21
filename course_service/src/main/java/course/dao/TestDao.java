package course.dao;

import course.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestDao extends JpaRepository<Test,Integer> {

    public List<Test> findByCourseID(int cid);
}
