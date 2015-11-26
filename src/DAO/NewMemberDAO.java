package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import DTO.MemberDTO;

public class NewMemberDAO implements MemberDAO{
	
	private JdbcTemplate template;
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public MemberDTO getMember(String EMAIL) throws ClassNotFoundException, SQLException {
		List<MemberDTO> results = template.query(
				"SELECT * FROM MEMBER WHERE email=?",
				new RowMapper<MemberDTO>() {
					@Override
					public MemberDTO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						MemberDTO member = new MemberDTO( //»ý¼ºÀÚ
								 rs.getString("email"),
								rs.getString("pwd"),
								 rs.getString("name"),
								 rs.getString("cPhone")
								);
						return member;
					}
				},
				EMAIL);

		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public int insert(final MemberDTO member) throws ClassNotFoundException, SQLException {
		 return	template.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					String sql = "INSERT INTO MEMBER(email, PWD, NAME, cPhone) VALUES( ?, ?, ?, ?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, member.getEmail());
					pstmt.setString(2, member.getPwd());
					pstmt.setString(3, member.getName());
					pstmt.setString(4, member.getcPhone());

					return pstmt;
				}
			});
			
	}

		
	
	
	
}
