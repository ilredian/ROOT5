package DTO;

public class MemberTypeDTO {

	private int typeno;
	private String typename;
	private String typetext;
	
	public String getTypetext() {
		return typetext;
	}
	public void setTypetext(String typetext) {
		this.typetext = typetext;
	}
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
