import javax.swing.JButton;

public class Buttons extends JButton{
	private int row,col,count;
	private boolean mine,flag;
	
	public Buttons(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public int getCount() {
		return count;
	}

	public boolean isMine() {
		return mine;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	
	

}
