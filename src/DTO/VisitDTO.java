package DTO;

public class VisitDTO {
	private String v_date;/* 작성자 */

	public String getV_date() {
		return v_date;
	}

	public void setV_date(String v_date) {
		this.v_date = v_date;
	}

	@Override
	public String toString() {
		return "VisitDTO [v_date=" + v_date + "]";
	}

	public VisitDTO(String v_date) {
		super();
		this.v_date = v_date;
	}
	 
	public VisitDTO(){}
	
}
