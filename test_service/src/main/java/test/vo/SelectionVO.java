package test.vo;

public class SelectionVO {
	private int selectionID;
	// 选项内容
	private String selection;
	private String optOrder;

	public SelectionVO(int selectionID, String selection, String optOrder) {
		this.selectionID = selectionID;
		this.selection = selection;
		this.optOrder = optOrder;
	}

	public int getSelectionID() {
		return selectionID;
	}

	public void setSelectionID(int selectionID) {
		this.selectionID = selectionID;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getOptOrder() {
		return optOrder;
	}

	public void setOptOrder(String optOrder) {
		this.optOrder = optOrder;
	}

}
