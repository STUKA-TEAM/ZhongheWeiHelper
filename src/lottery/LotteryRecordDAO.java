package lottery;

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
 * @Title: LotteryRecordDAO
 * @Description: DAO for LotteryRecord model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月24日
 */
public class LotteryRecordDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertLotteryRecord
	 * @Description: 插入一条用户抽奖记录
	 * @param lRecord
	 * @return
	 */
	public int insertLotteryRecord(final LotteryRecord lRecord){
		int result = 0;
		final String SQL = "INSERT INTO lottery_record VALUES(default, ?, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, lRecord.getLotteryId());
				ps.setString(2, lRecord.getOpenId());
				ps.setInt(3, lRecord.getLotteryResult());
				return ps;
			}
		}, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	
	//query
	/**
	 * @Title: getNumByOpenId
	 * @Description: 查询某一用户在一个活动中的参与次数
	 * @param openId
	 * @param lotteryId
	 * @return
	 */
	public int getChanceUsed(String openId, int lotteryId){
		String SQL = "SELECT COUNT(*) FROM lottery_record WHERE OpenId = ? AND LotteryId = ?";
		int result = jdbcTemplate.queryForObject(SQL, Integer.class, openId, lotteryId);
		return result;
	}
}
