package DTO;

import java.sql.Date;

public class BoardNoticeDTO {

	private int boardno; //게시판번호
	private String title;/* 제목 */
	private String content; /* 내용 */
	private Date regdate; /* 작성일 */
	private int countno;/* 조회수 */
	private int active; /* 삭제여부 */
	private int critical; /* 강조여부 */
	private int categoryno; /* 카테고리번호 */
	
	
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
	public BoardNoticeDTO(int boardno, String title, String content, Date regdate, int countno, int active,
			int critical, int categoryno) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.countno = countno;
		this.active = active;
		this.critical = critical;
		this.categoryno = categoryno;
	}

	
	


	/*
	 * 자유게시판 글번호 SEQ N/A NULL 제목 TITLE N/A NULL 글내용 CONTENT N/A NULL 작성일 REGDATE
	 * N/A NULL 조회수 HIT N/A NULL
	 * 
	 * 그룹번호 GROUPNO N/A NULL 답글 STEP N/A NULL 새 컬럼4 DEPTH N/A NULL
	 */

}
