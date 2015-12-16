package DAO;

import java.util.List;

import DTO.BoardPhotoDTO;
import DTO.ReportPhotoDTO;

public interface ReportPhotoDAO {
	public int getReportPhotoCount(String field, String query, int categoryno) throws Exception;

	// 신고 여부 확인하기
	public int isReportPhoto(int boardno, int memberno) throws Exception;
	
	// 자유 게시판 신고된 정보 불러오기
	public BoardPhotoDTO getReportPhoto(int boardno) throws Exception;
	
	// 신고한 사람이 많은 순으로 불러오기
	public List<Integer> getReportPhotono(int start, int categoryno, int pageSize) throws Exception;
	
	// 게시글 신고하기
	public int insertReportPhoto(ReportPhotoDTO reportPhotoDTO) throws Exception;
	
	// 게시된 글이 완전 삭제처리 됐을시
	public int deleteReportPhoto(int boardno) throws Exception;
}
