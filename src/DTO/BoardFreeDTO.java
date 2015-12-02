package DTO;

import java.sql.Date;

public class BoardFreeDTO {
	
	private int boardno;    //시퀀스
	private String title;	//제목
	private String name;	//작성자 이름	
	private String content;	//글 내용
	private String regdate;	//작성일
	private int countno;		//조회수
	private int active;
	
	private int groupno; 	 
	private int step;
	private int depth;
	private int notice ;
	private int memberno; 
	private int categoryno;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getNotice() {
		return notice;
	}
	public void setNotice(int notice) {
		this.notice = notice;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}
	public int getCategoryno() {
		return categoryno;
	}
	public void setCategoryno(int categoryno) {
		this.categoryno = categoryno;
	}
	@Override
	public String toString() {
		return "BoardFreeDTO [boardno=" + boardno + ", title=" + title + ", name=" + name + ", content=" + content
				+ ", regdate=" + regdate + ", countno=" + countno + ", active=" + active + ", groupno=" + groupno
				+ ", step=" + step + ", depth=" + depth + ", notice=" + notice + ", memberno=" + memberno
				+ ", categoryno=" + categoryno + "]";
	}
	
}
