package DTO;

public class QueryDTO {//검색어 빅 데이터
	private String query;// 검색어
	private int count;// 검색된 query 횟수
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
