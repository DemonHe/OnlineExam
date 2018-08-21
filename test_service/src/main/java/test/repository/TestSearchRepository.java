package test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.domain.Student;
import test.vo.SimpleTestVO;

public interface TestSearchRepository extends JpaRepository<Student, Integer> {

	@Query("select new test.vo.SimpleTestVO(t.id,c.name,t.start,t.end,0.0)"
			+ " FROM Test t,Course c,StudentList sl,Student s where s.sid=?1"
			+ " and s.email=sl.email and sl.testID=t.id and t.courseID=c.id and t.start > ?2")
	public List<SimpleTestVO> findUndone(String sid, String date);

	@Query("select new test.vo.SimpleTestVO" + "(t.id,c.name,t.start,t.end,0.0)"
			+ " FROM Test t,Course c,StudentList sl,Student s,MarkList m where s.sid=?1"
			+ " and s.email=sl.email and sl.testID=t.id and t.courseID=c.id and t.start <= ?2 and t.end >= ?3"
			+ " and m.testID=t.id and m.sid=s.sid and m.isSubmit=0")
	public List<SimpleTestVO> findProcessing(String sid, String date1, String date2);

	@Query("select new test.vo.SimpleTestVO" + "(t.id,c.name,t.start,t.end,m.mark)"
			+ " FROM Test t,Course c,StudentList sl,Student s,MarkList m where s.sid=?1"
			+ " and s.email=sl.email and sl.testID=t.id and t.courseID=c.id and m.sid=s.sid and m.testID=t.id"
			+ " and (t.end < ?2 or m.isSubmit=1)")
	public List<SimpleTestVO> findAccomplished(String sid, String date);
}
