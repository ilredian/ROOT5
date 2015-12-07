package DTO;

public class BoardPhotoDTO {
	private int boardno;
	private String title;
	private String name;
	private String content;
	private String regdate;
	private int countno;
	private int active;
	private String uploadfile;
	private int memberno;
	private int categoryno;
	
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
	public String getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
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
