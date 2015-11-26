//package DAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.RowMapper;
//import org.mybatis.spring.support.SqlSessionDaoSupport;
//import DTO.MemberDTO;
//import DAO.MemberDAO;
//public class NewMemberDAO extends SqlSessionDaoSupport implements MemberDAO{
//	
//	private JdbcTemplate template;
//	@Autowired
//	public void setTemplate(JdbcTemplate template) {
//		this.template = template;
//	}
//	
//	@Override
//	public MemberDTO getMember(String Email) throws ClassNotFoundException, SQLException {
//		return (MemberDTO) getSqlSession().selectOne("MemberDAO.getMember",Email);
//		
//		/*		List<MemberDTO> results = template.query(
//				"SELECT * FROM MEMBER WHERE email=?",
//				new RowMapper<MemberDTO>() {
//					@Override
//					public MemberDTO mapRow(ResultSet rs, int rowNum)
//							throws SQLException {
//						MemberDTO member = new MemberDTO( //»ý¼ºÀÚ
//								 rs.getString("email"),
//								rs.getString("pwd"),
//								 rs.getString("name"),
//								 rs.getString("cPhone")
//								);
//						return member;
//					}
//				},
//				EMAIL);
//
//		return results.isEmpty() ? null : results.get(0);*/
//	}
//
//	@Override
//	public int insert(final MemberDTO member) throws ClassNotFoundException, SQLException {
//		return (Integer) getSqlSession().selectOne("MemberDAO.insert",member);
//
//		/*		 return	template.update(new PreparedStatementCreator() {
//				@Override
//				public PreparedStatement createPreparedStatement(Connection con)
//						throws SQLException {
//					String sql = "INSERT INTO MEMBER(email, PWD, NAME, cPhone) VALUES( ?, ?, ?, ?)";
//					PreparedStatement pstmt = con.prepareStatement(sql);
//					pstmt.setString(1, member.getEmail());
//					pstmt.setString(2, member.getPwd());
//					pstmt.setString(3, member.getName());
//					pstmt.setString(4, member.getcPhone());
//					return pstmt;
//				}
//			});
//	}*/
//
//		}
//	
//	
//	
//}
