package time.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import time.domain.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {

	@Query("select t.start from Test t where t.id=?1")
	public String findStartById(@Param("id") int id);

	@Query("select t.end from Test t where t.id=?1")
	public String findEndById(@Param("id") int id);
}
