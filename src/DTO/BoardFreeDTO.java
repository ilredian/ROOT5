package DTO;

import java.sql.Date;

public class BoardFreeDTO {
	
	private int boardno;    //시퀀스
	private String title;	//제목
	private String name;	//작성자 이름	
	private String content;	//글 내용
	private Date regdate;	//작성일
	private int countno;		//조회수
	private int active;
	
	private int groupno; 	 
	private int step;
	private int depth;
	private int notice ;
	private int memberno; 
	private int categoryno;
	
	public BoardFreeDTO() {
		super();
	}

	public BoardFreeDTO(int boardno, String title, String name, String content, Date regdate, int countno, int active,
			int groupno, int step, int depth, int notice, int memberno, int categoryno) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.name = name;
		this.content = content;
		this.regdate = regdate;
		this.countno = countno;
		this.active = active;
		this.groupno = groupno;
		this.step = step;
		this.depth = depth;
		this.notice = notice;
		this.memberno = memberno;
		this.categoryno = categoryno;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
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
