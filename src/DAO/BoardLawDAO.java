package DAO;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import DTO.BoardFreeDTO;
import DTO.BoardLawDTO;
public interface BoardLawDAO {
    //게시물 개수
    public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
    //전체 게시물
    public List<BoardLawDTO> getNotices(int start, String field, String query, int pagerSize) throws Exception;
    //게시물 삭제int start, String field, String query, int pagerSize
    public int delete(int boardno) throws ClassNotFoundException, SQLException;
    //게시물 수정
    public int update(BoardLawDTO boardLawDTO) throws ClassNotFoundException, SQLException;
    //게시물 상세
    public BoardLawDTO getNotice(int boardno) throws ClassNotFoundException, SQLException;
    //게시물 입력
    public int insert(BoardLawDTO n) throws ClassNotFoundException, SQLException;

    ///추가 12월 7일
 // 게시물 조회수 증가
 	public int updateCountno(int boardno) throws Exception;
    
	// 답글 순서 정리
	public int updateStep(int groupno, int step) throws Exception;
	
	// 게시물 답글 달기
	public int answer(BoardLawDTO boardLawDTO) throws Exception;
	
    
}
