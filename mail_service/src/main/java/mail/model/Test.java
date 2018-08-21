package mail.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 出题老师设置考试信息，如开考时间，结束时间，题目数
 * 
 * @author 云奎
 *
 */
@Entity
public class Test {

	@Id
	@GeneratedValue
	// 此处id即为其他表的testID，考试号
	private int id;
	// 开始时间，精确到秒 YYYY-MM-DD HH:mm:ss
	private String start;
	// 结束时间，精确到秒 YYYY-MM-DD HH:mm:ss
	private String end;
	// 课程号
	private int courseID;
	//题目数
	private int qnum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getQnum() {
		return qnum;
	}

	public void setQnum(int qnum) {
		this.qnum = qnum;
	}

	public String toString(){
		return "id: "+id+", start: "+start+", end:"+end+", courseID: "+courseID+", qnum: "+qnum;
	}
}
