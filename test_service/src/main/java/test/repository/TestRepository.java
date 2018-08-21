package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.domain.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {

	@Query("select t.courseID from Test t where t.id=?1")
	public int findCourse(int tid);
}
