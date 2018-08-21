package result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import result.domain.Selection;

/**
 * Created by Kimone.
 */
public interface SelectionRepository extends JpaRepository<Selection, Integer> {
    public Selection findById(int id);
}
