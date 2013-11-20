package vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @Title: AdviceDAO
 * @Description: DAO for Advice model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月20日
 */
public class AdviceDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertAdvice
	 * @Description: 插入一条用户评论记录
	 * @param advice
	 * @return
	 */
	public int insertAdvice(final Advice advice){
		int result = 0;
		final String SQL = "INSERT INTO vote_advice(VoteId, OpenId, AdviceContent) VALUES (?, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement ps =
	                connection.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
	            ps.setInt(1, advice.getVoteId());
	            ps.setString(2, advice.getOpenId());
	            ps.setString(3, advice.getAdviceContent());
	            return ps;
	        }
	    }, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	
	//delete
	/**
	 * @Title: deleteAdvice
	 * @Description: 根据活动Id删除活动相关的评论信息
	 * @param voteId
	 * @return
	 */
	public int deleteAdvice(int voteId){
		String SQL = "DELETE FROM vote_advice WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, voteId);
		return effectedRowNum;
	}
	
	//query
	/**
	 * @Title: getAdviceList
	 * @Description: 根据活动Id查找相对应的所有评论记录
	 * @param voteId
	 * @return
	 */
	public List<Advice> getAdviceList(int voteId){
		String SQL = "SELECT * FROM vote_advice WHERE VoteId = ?";
		List<Advice> aList = jdbcTemplate.query(SQL, new Object[]{voteId}, new AdviceFullMapper());
		return aList;
	}
	
	private static final class AdviceFullMapper implements RowMapper<Advice> {
		@Override
		public Advice mapRow(ResultSet rs, int arg1) throws SQLException {
			Advice advice = new Advice();
			advice.setAdviceId(rs.getInt("AdviceId"));
			advice.setVoteId(rs.getInt("VoteId"));
			advice.setOpenId(rs.getString("OpenId"));
			advice.setAdviceContent(rs.getString("AdviceContent"));
			return advice;
		}		
	}
}
