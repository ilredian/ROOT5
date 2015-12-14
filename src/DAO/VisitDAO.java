package DAO;

public interface VisitDAO {

	//방문자수 증가
	public int setVisitTotalCount() throws Exception;
	
	//전체 방문자 수 조회
	public int getVisitTotalCount() throws Exception;
	
	//오늘 방문자 수 조회
	public int getVisitTodayCount() throws Exception;
	
}
