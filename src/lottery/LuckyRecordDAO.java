package lottery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

/**
 * @Title: LuckyRecordDAO
 * @Description: DAO for LuckyRecord model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月24日
 */
public class LuckyRecordDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertLuckyRecord
	 * @Description: 插入一条用户中奖记录
	 * @param lRecord
	 * @return
	 */
	public int insertLuckyRecord(final LuckyRecord lRecord){
		int result = 0;
		final String SQL = "INSERT INTO lottery_lucky_record VALUES(default, ?, ?, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, lRecord.getPrizeId());
				ps.setString(2, lRecord.getOpenId());
				ps.setString(3, lRecord.getPrizeKey());
				ps.setInt(4, lRecord.getPrizeStatus());
				return ps;
			}
		}, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	
	//update
	/**
	 * @Title: updateStatusByLuckyId
	 * @Description: 根据中奖Id更新奖品领取状态
	 * @param luckyId
	 * @param prizeStatus
	 * @return
	 */
	public int updateStatusByLuckyId(int luckyId, int prizeStatus){
		String SQL = "UPDATE lottery_lucky_record SET PrizeStatus = ? WHERE LuckyId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, new Object[]{prizeStatus, luckyId});
		return effectedRowNum;
	}
	
	/**
	 * @Title: updateStatusByPrizeKey
	 * @Description: 根据兑奖码更新奖品领取状态
	 * @param prizeKey
	 * @param prizeStatus
	 * @return
	 */
	public int updateStatusByPrizeKey(String prizeKey, int prizeStatus){
		String SQL = "UPDATE lottery_lucky_record SET PrizeStatus = ? WHERE PrizeKey = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, new Object[]{prizeStatus, prizeKey});
		return effectedRowNum;
	}
	
	/**
	 * @Title: updatePrizeKeyByLuckyId
	 * @Description: 根据中奖Id更新兑奖码
	 * @param luckyId
	 * @param prizeKey
	 * @return
	 */
	public int updatePrizeKeyByLuckyId(int luckyId, String prizeKey){
		String SQL = "UPDATE lottery_lucky_record SET PrizeKey = ? WHERE LuckyId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, prizeKey, luckyId);
		return effectedRowNum;
	}
	
	//query
	/**
	 * @Title: getActualNum
	 * @Description: 查询某一奖项实际中奖人数
	 * @param prizeId
	 * @return
	 */
	public int getActualNum(int prizeId){
		String SQL = "SELECT COUNT(*) FROM lottery_lucky_record WHERE PrizeId = ?";
		int result = jdbcTemplate.queryForObject(SQL, Integer.class, prizeId);
		return result;
	}
	
	/**
	 * @Title: getNumByPrizeKey
	 * @Description: 查询兑奖码对应的记录数
	 * @param prizeKey
	 * @return 0或1
	 */
	public int getNumByPrizeKey(String prizeKey){
		String SQL = "SELECT COUNT(*) FROM lottery_lucky_record WHERE PrizeKey = ?";
		int result = jdbcTemplate.queryForObject(SQL, Integer.class, prizeKey);
		return result;
	}
	
	/**
	 * @Title: getRecordByPrizeKey
	 * @Description: 根据兑奖码查询中奖记录详细信息， 前提是兑奖码与LuckyId一一映射
	 * @param prizeKey
	 * @return
	 */
	public LuckyRecord getRecordByPrizeKey(String prizeKey){
		String SQL = "SELECT * FROM lottery_lucky_record WHERE PrizeKey = ?";
		LuckyRecord lRecord = jdbcTemplate.queryForObject(SQL, new Object[]{prizeKey}, new LuckyRecordFullMapper());
		return lRecord;
	}
	
    private static final class LuckyRecordFullMapper implements RowMapper<LuckyRecord>{
		@Override
		public LuckyRecord mapRow(ResultSet rs, int arg1) throws SQLException {
			LuckyRecord lRecord = new LuckyRecord();
			lRecord.setLuckyId(rs.getInt("LuckyId"));
			lRecord.setPrizeId(rs.getInt("PrizeId"));
			lRecord.setOpenId(rs.getString("OpenId"));
			lRecord.setPrizeKey(rs.getString("PrizeKey"));
			lRecord.setPrizeStatus(rs.getInt("PrizeStatus"));
			return lRecord;
		}   	
    }
}
