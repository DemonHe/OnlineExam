package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 出题老师设置第几道题几分，根据分数找到合适的题目生成试卷
 * 
 * @author 云奎
 *
 */
@Entity
public class ScorePerQuestion {

	@Id
	@GeneratedValue
	private int id;
	// 考试号
	private int testID;
	// 顺序号，如第一题，第二题。。。
	private int qid;
	private double score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestID() {
		return testID;
	}

	public void setTestID(int testID) {
		this.testID = testID;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
