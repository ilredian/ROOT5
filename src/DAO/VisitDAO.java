package DAO;

import DTO.VisitDTO;

public interface VisitDAO {

	public int setVisitTotalCount() throws Exception;
	
	public int getVisitTotalCount() throws Exception;
	public int getVisitTodayCount() throws Exception;
/*	INSERT INTO VISIT (V_DATE) VALUES (sysdate); -- 전체 방문자 수 증가 setVisitTotalCount()

	select count(*) from visit; -- 전체 방문자 수 조회 getVisitTotalCount()

	select count(*) from visit   -- 오늘의 방문자 수 조회 getVisitTodayCount()
	where substr(to_char(v_date), 1, 9) = to_date(sysdate, 'yy/MM/dd');*/
	
	
}
