package vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

/**
 * @Title: VoteActivityDAO
 * @Description: DAO for VoteActivity model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月20日
 */
public class VoteActivityDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertActivity
	 * @Description: 向数据库中插入一条活动记录
	 * @param vActivity
	 * @return
	 */
	public int insertActivity(final VoteActivity vActivity){
		int result = 0;
		final String SQL = "INSERT INTO vote_activity VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, vActivity.getVoteTitle());
				ps.setString(2, vActivity.getVoteSummary());
				ps.setString(3, vActivity.getVotePicture());
				ps.setTimestamp(4, vActivity.getStartDate());
				ps.setTimestamp(5, vActivity.getEndDate());
				ps.setInt(6, vActivity.getIsMultiChoice());
				ps.setInt(7, vActivity.getMaxChoice());
				ps.setInt(8, vActivity.getEnableAdvice());
				ps.setInt(9, vActivity.getVoteStatus());
				return ps;
			}
		}, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
}
