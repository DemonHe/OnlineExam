package analyse.repository;

import analyse.domain.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface TestRepository extends JpaRepository<Test, Integer> {

    public Test findById(int id);
}
