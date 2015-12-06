package DTO;

public class BoardCategoryDTO {

	private int categoryno;
	private String categoryname;
	
	public int getCategoryno() {
		return categoryno;
	}
	public void setCategoryno(int categoryno) {
		this.categoryno = categoryno;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	@Override
	public String toString() {
		return "BoardCategoryDTO [categoryno=" + categoryno + ", categoryname=" + categoryname + "]";
	}
}
