package analyse.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 记录学生答题信息和试卷信息
 * 
 * @author 云奎
 *
 */
@Entity
public class AnswerRecord {

	@Id
	@GeneratedValue
	private int id;
	private int testID;
	private String sid;
	private int qid;
	private int selectionID;
	// 顺序，A，B，C，D。。。
	private String optOrder;
	// 1选择，0未选择
	private int isSelected;

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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public int getSelectionID() {
		return selectionID;
	}

	public void setSelectionID(int selectionID) {
		this.selectionID = selectionID;
	}

	public String getOptOrder() {
		return optOrder;
	}

	public void setOptOrder(String optOrder) {
		this.optOrder = optOrder;
	}

	public int getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}

}
