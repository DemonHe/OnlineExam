package excel.dao;

import excel.model.Selection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionDao extends JpaRepository<Selection,Integer> {

    public Selection save(Selection selection);
}
