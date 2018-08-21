package excel.dao;

import excel.model.MarkList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkListDao extends JpaRepository<MarkList, Integer> {

    public MarkList save(MarkList markList);
}
