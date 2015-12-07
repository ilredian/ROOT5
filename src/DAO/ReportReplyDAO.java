package DAO;

import java.util.List;

import DTO.ReportReplyDTO;

public interface ReportReplyDAO {

	// 신고된 리플 수
	public int getReportReplyCount(String field, String query) throws Exception;
	
	// 신고한 사람이 많은 순으로 불러오기
	public List<Integer> getReportReplyno(int start, int pageSize) throws Exception;
	
	// 신고 했는지 확인
	public int getInsertReply(ReportReplyDTO reportReplyDTO) throws Exception;
	
	// 신고 댓글 등록
	public int insertReportReply(ReportReplyDTO reportReplyDTO) throws Exception;
	
	// 댓글 삭제시
	public int deleteReportReply(int replyno) throws Exception;
}
