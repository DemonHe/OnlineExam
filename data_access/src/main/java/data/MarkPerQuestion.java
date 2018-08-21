package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 每位学生的某考试一道题的得分，方便后面查看
 * 
 * @author 云奎
 *
 */
@Entity
public class MarkPerQuestion {

	@Id
	@GeneratedValue
	private int id;
	//考试号
	private int testID;
	//题号
	private int qid;
	//学号
	private String sid;
	private double mark;

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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

}
