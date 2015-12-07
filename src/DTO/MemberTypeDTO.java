package DTO;

public class MemberTypeDTO {

	private int typeno;
	private String typename;
	
	public int getTypeno() {
		return typeno;
	}
	public void setTypeno(int typeno) {
		this.typeno = typeno;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	@Override
	public String toString() {
		return "MemberTypeDTO [typeno=" + typeno + ", typename=" + typename + "]";
	}
}
