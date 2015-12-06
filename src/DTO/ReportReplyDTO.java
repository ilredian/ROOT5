package DTO;

public class ReportReplyDTO {

	private int categoryno;// 신고된 카테고리 번호
	private int boardno;// 신고된 게시물 번호
	private int replyno;// 신고된 리플 번호
	private int memberno;// 신고한 멤버 번호
	
	public int getCategoryno() {
		return categoryno;
	}
	public void setCategoryno(int categoryno) {
		this.categoryno = categoryno;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public int getReplyno() {
		return replyno;
	}
	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}
	
	@Override
	public String toString() {
		return "ReportReplyDTO [categoryno=" + categoryno + ", boardno=" + boardno + ", replyno=" + replyno + ", memberno="
				+ memberno + "]";
	}
}
