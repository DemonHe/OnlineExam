package data_access;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import data.AnswerRecord;

/**
 * 随机生成试卷
 * 
 * @author 云奎
 *
 */
public interface TestInitRepository extends JpaRepository<AnswerRecord, Integer> {

	public List<AnswerRecord> findBySid(String sid);
}
