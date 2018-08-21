package test.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 每场考试要参加考试的学生邮箱，由excel导入
 * 
 * @author 云奎
 *
 */
@Entity
public class StudentList {

	@Id
	@GeneratedValue
	private int id;
	private int testID;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
