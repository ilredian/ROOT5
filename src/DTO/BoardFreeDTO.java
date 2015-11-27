package DTO;

import java.util.Date;

public class BoardFreeDTO {
	
	private String seq;    //시퀀스
	private String title;	//제목
	private String content;	//글 내용
	private Date regdate;	//작성일
	private int hit;		//조회수
	
	private int groupno; 	
	private int step;
	private int depth;
	
	//////////////////
	
	
	@Override
	public String toString() {
		return "BoardFreeDTO [seq=" + seq + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ ", hit=" + hit + ", groupno=" + groupno + ", step=" + step + ", depth=" + depth + "]";
	}

	public BoardFreeDTO() {
		super();
	}
	
	public BoardFreeDTO(String seq, String title, String content, Date regdate, int hit, int groupno, int step,
			int depth) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.groupno = groupno;
		this.step = step;
		this.depth = depth;
	}
	



	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
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
	
	
/*	자유게시판
	글번호	SEQ	N/A	NULL
	제목	TITLE	N/A	NULL
	글내용	CONTENT	N/A	NULL
	작성일	REGDATE	N/A	NULL
	조회수	HIT	N/A	NULL

	그룹번호	GROUPNO	N/A	NULL
	답글	STEP	N/A	NULL
	새 컬럼4	DEPTH	N/A	NULL
*/
	
}
