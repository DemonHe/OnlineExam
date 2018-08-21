package attribute.dao;

import attribute.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDao extends JpaRepository<Test, Integer> {

    public Test save(Test test);
}
