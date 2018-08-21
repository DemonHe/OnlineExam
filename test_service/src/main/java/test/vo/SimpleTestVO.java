package test.vo;

public class SimpleTestVO {

	private int tid;
	private String cname;
	private String start;
	private String end;
	private double mark;

	public SimpleTestVO(int tid, String cname, String start, String end, double mark) {
		this.tid = tid;
		this.cname = cname;
		this.start = start;
		this.end = end;
		this.mark = mark;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

}
