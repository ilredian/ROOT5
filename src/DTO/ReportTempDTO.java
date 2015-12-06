package DTO;

public class ReportTempDTO {//신고 처리를 하기 위한 임시 DTO
	
	private int boardno;
	private int count;
	
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "ReportTempDTO [boardno=" + boardno + ", count=" + count + "]";
	}
}
