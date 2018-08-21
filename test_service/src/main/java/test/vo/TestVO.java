package test.vo;

import java.util.ArrayList;

public class TestVO {

	private int testID;
	private String sid;
	private int qid;
	// 内容
	private String question;
	private ArrayList<SelectionVO> list;
	private double score;

	public TestVO(int testID, String sid, int qid, String question, ArrayList<SelectionVO> list, double score) {
		this.testID = testID;
		this.sid = sid;
		this.qid = qid;
		this.question = question;
		this.list = list;
		this.score = score;
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<SelectionVO> getList() {
		return list;
	}

	public void setList(ArrayList<SelectionVO> list) {
		this.list = list;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
