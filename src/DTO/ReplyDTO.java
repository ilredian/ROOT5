package DTO;

import java.sql.Date;

public class ReplyDTO {
	private int replyno;/* 댓글번호 */
	private String name;/* 작성자 */
	private String content;/* 내용 */
	private Date regdate;/* 작성일 */
	private int active;/* 삭제여부 DEFAULT 0*/
	private int groupno;/* 그룹 */
	private int step;/* 순서 DEFAULT 0*/
	private int depth;/* 들여쓰기 DEFAULT 0*/
	private int boardno;/* 게시판번호 */
	private int memberno;/* 회원번호 */
	private int categoryno;/* 카테고리번호 */
	
	public int getReplyno() {
		return replyno;
	}
	public void setReplyno(int replyno) {
		this.replyno = replyno;
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
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
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
}
