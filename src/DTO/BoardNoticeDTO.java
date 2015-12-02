package DTO;

public class BoardNoticeDTO {

	private int boardno; //게시판번호
	private String title;/* 제목 */
	private String content; /* 내용 */
	private String regdate; /* 작성일 */
	private int countno;/* 조회수 */
	private int active; /* 삭제여부 */
	private int critical; /* 강조여부 */
	private int categoryno; /* 카테고리번호 */
	
	private int boardReplyCount;//게시글 당 리플 갯수
	
	public int getBoardReplyCount() {
		return boardReplyCount;
	}
	public void setBoardReplyCount(int boardReplyCount) {
		this.boardReplyCount = boardReplyCount;
	}
	
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getCountno() {
		return countno;
	}
	public void setCountno(int countno) {
		this.countno = countno;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getCritical() {
		return critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	public int getCategoryno() {
		return categoryno;
	}
	public void setCategoryno(int categoryno) {
		this.categoryno = categoryno;
	}
	@Override
	public String toString() {
		return "BoardNoticeDTO [boardno=" + boardno + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", countno=" + countno + ", active=" + active + ", critical=" + critical + ", categoryno="
				+ categoryno + "]";
	}
}
