package test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.domain.AnswerRecord;

public interface AnswerRecordRepositiry extends JpaRepository<AnswerRecord, Integer> {

	public List<AnswerRecord> findByTestIDAndSid(int tid, String sid);

	@Query("select count(a.qid) from AnswerRecord a where a.testID=?1 and a.sid=?2")
	public int count(int tid, String sid);
}
