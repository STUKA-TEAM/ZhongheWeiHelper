package lottery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tools.Constant;

import com.mysql.jdbc.Statement;

/**
 * @Title: LotteryActivityDAO
 * @Description: DAO for LotteryActivity model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月23日
 */
public class LotteryActivityDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertActivity
	 * @Description: 向数据库中插入一条活动记录以及相关的选项信息记录
	 * @param lActivity
	 * @return
	 */
	public int insertActivity(final LotteryActivity lActivity){
		int result = 0;
		final String SQL = "INSERT INTO lottery_activity VALUES(default, ?, ?, ?, ?, ?, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, lActivity.getLotteryName());
				ps.setString(2, lActivity.getLotterySummary());
				ps.setString(3, lActivity.getLotteryPicture());
				ps.setTimestamp(4, lActivity.getStartDate());
				ps.setTimestamp(5, lActivity.getEndDate());
				ps.setInt(6, lActivity.getChanceNum());
				ps.setInt(7, lActivity.getLotteryStatus());
				return ps;
			}
		}, kHolder);
		
		if (result > 0 && lActivity.getLpList() != null) {    //insert items
			int lotteryId = kHolder.getKey().intValue();
			List<LotteryPrize> pList = lActivity.getLpList();

			/** 按中奖率由小到大排序 */
			Collections.sort(pList, new Comparator<LotteryPrize>() {
				@Override
				public int compare(LotteryPrize o1, LotteryPrize o2) {
					return o1.getLuckyPercent().compareTo(o2.getLuckyPercent());
				}
			});

			for (int i = 0; i < pList.size(); i++) {
				LotteryPrize lPrize = pList.get(i);
				insertPrize(lPrize, lotteryId);
			}
		}
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	
	//update
	/**
	 * @Title: updateActivity
	 * @Description: 更新抽奖活动信息记录以及关联记录
	 * @param lActivity
	 * @return
	 */
	public int updateActivity(LotteryActivity lActivity){
		String SQL = "UPDATE lottery_activity SET LotteryName = ?, LotterySummary = ?, "
				+ "LotteryPicture = ?, StartDate = ?, EndDate = ?, ChanceNum = ?, "
				+ "LotteryStatus = ? WHERE LotteryId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, new Object[]{lActivity.getLotteryName(), 
				lActivity.getLotterySummary(), lActivity.getLotteryPicture(), lActivity.getStartDate(),
				lActivity.getEndDate(), lActivity.getChanceNum(), lActivity.getLotteryStatus(), lActivity.getLotteryId()});
		
		if (effectedRowNum > 0) {  //delete old items and insert new items
			deletePrize(lActivity.getLotteryId());
			
			List<LotteryPrize> pList = lActivity.getLpList();
			
			/**按中奖率由小到大排序*/
			Collections.sort(pList, new Comparator<LotteryPrize>() {
				@Override
				public int compare(LotteryPrize o1, LotteryPrize o2) {
					return o1.getLuckyPercent().compareTo(o2.getLuckyPercent());
				}
			});
			
			for (int i = 0; i < pList.size(); i++) {
				LotteryPrize lPrize = pList.get(i);
				insertPrize(lPrize, lActivity.getLotteryId());			
			}
		}
		return effectedRowNum;
	}
	
	/**
	 * @Title: updateStatus
	 * @Description: 改变指定活动Id的活动状态
	 * @param lotteryId
	 * @param lotteryStatus
	 * @return
	 */
	public int updateStatus(int lotteryId, int lotteryStatus){
		String SQL = "UPDATE lottery_activity SET LotteryStatus = ? WHERE LotteryId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, lotteryStatus, lotteryId);
		return effectedRowNum;
	}
	
    /**
     * @Title: updateStartDateAndStatus
     * @Description: 用于发布活动
     * @param lotteryId
     * @param startDate
     * @param lotteryStatus
     * @return
     */
	public int updateStartDateAndStatus(int lotteryId, Timestamp startDate, int lotteryStatus){
		String SQL = "UPDATE lottery_activity SET StartDate = ?, LotteryStatus = ? WHERE LotteryId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, startDate, lotteryStatus, lotteryId);
		return effectedRowNum;
	}
	
	/**
	 * @Title: updateEndDateAndStatus
	 * @Description: 用于关闭活动
	 * @param lotteryId
	 * @param endDate
	 * @param lotteryStatus
	 * @return
	 */
	public int updateEndDateAndStatus(int lotteryId, Timestamp endDate, int lotteryStatus){
		String SQL = "UPDATE lottery_activity SET EndDate = ?, LotteryStatus = ? WHERE LotteryId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, endDate, lotteryStatus, lotteryId);
		return effectedRowNum;
	}
	
	//delete
	/**
	 * @Title: deleteByLotteryId
	 * @Description: 根据活动Id删除活动记录以及与之相关的一切信息记录;
	 * 间接记录的删除只关注执行与否，不统计影响行数
	 * @param lotteryId
	 * @return
	 */
	public int deleteByLotteryId(int lotteryId){
		String SQL = "DELETE FROM lottery_activity WHERE LotteryId = ?";
		int effectedRowNum = 0;
		
		int lotteryStatus = getLotteryStatus(lotteryId);
		switch (lotteryStatus) {
		case Constant.ACTIVITY_DRAFT_STATUS:
		case Constant.ACTIVITY_SAVE_STATUS:
			deletePrize(lotteryId);
			effectedRowNum = jdbcTemplate.update(SQL, lotteryId);
			break;
		case Constant.ACTIVITY_CLOSED_STATUS:
			List<Integer> iList = getPrizeIdList(lotteryId);			
			for (int i = 0; i < iList.size(); i++) {
				deleteLuckyRecord(iList.get(i));
			}
			deleteLotteryRecord(lotteryId);
			deletePrize(lotteryId);
			effectedRowNum = jdbcTemplate.update(SQL, lotteryId);
			break;
		default:
			effectedRowNum = 0;
			break;
		}
		
		return effectedRowNum;
	}
	
	//query
	/**
	 * @Title: getActivity
	 * @Description: 根据活动Id查询一条活动记录（配置信息）
	 * @param lotteryId
	 * @return
	 */
	public LotteryActivity getActivity(int lotteryId){
		String SQL = "SELECT * FROM lottery_activity WHERE LotteryId = ?";
		LotteryActivity lActivity = jdbcTemplate.queryForObject(SQL, new Object[]{lotteryId}, new LotteryActivityFullMapper());
		
		List<LotteryPrize> pList = getLotteryPrizeList(lotteryId); 
		lActivity.setLpList(pList);
		
		return lActivity;
	}
	
	/**
	 * @Title: getActivityListByStatus
	 * @Description: 根据活动状态获取一组活动记录信息
	 * @param lotteryStatus
	 * @return
	 */
	public List<LotteryActivity> getActivityListByStatus(int lotteryStatus){
		String SQL = "SELECT * FROM lottery_activity WHERE LotteryStatus = ?";
		List<LotteryActivity> aList = jdbcTemplate.query(SQL, new Object[]{lotteryStatus}, new LotteryActivityFullMapper());
		
		for (int i = 0; i < aList.size(); i++) {
			LotteryActivity lActivity = aList.get(i);
			lActivity.setLpList(getLotteryPrizeList(lActivity.getLotteryId()));
		}
		
		return aList;
	}
	
	/**
	 * @Title: getActivityList
	 * @Description: 获取数据库中所有活动配置信息记录
	 * @return
	 */
	public List<LotteryActivity> getActivityList(){
		String SQL = "SELECT * FROM lottery_activity";
		List<LotteryActivity> aList = jdbcTemplate.query(SQL, new LotteryActivityFullMapper());
		
		for (int i = 0; i < aList.size(); i++) {
			LotteryActivity lActivity = aList.get(i);
			lActivity.setLpList(getLotteryPrizeList(lActivity.getLotteryId()));
		}
		
		return aList;
	}
	
	private static final class LotteryActivityFullMapper implements RowMapper<LotteryActivity> {
		@Override
		public LotteryActivity mapRow(ResultSet rs, int arg1)
				throws SQLException {
			LotteryActivity lActivity = new LotteryActivity();
            lActivity.setLotteryId(rs.getInt("LotteryId"));
            lActivity.setLotteryName(rs.getString("LotteryName"));
            lActivity.setLotterySummary(rs.getString("LotterySummary"));
            lActivity.setLotteryPicture(rs.getString("LotteryPicture"));
            lActivity.setStartDate(rs.getTimestamp("StartDate"));
            lActivity.setEndDate(rs.getTimestamp("EndDate"));
            lActivity.setChanceNum(rs.getInt("ChanceNum"));
            lActivity.setLotteryStatus(rs.getInt("LotteryStatus"));
			return lActivity;
		}		
	}
	
	/**
	 * @Title: getLotteryStatus
	 * @Description: 根据活动Id返回活动当前状态
	 * @param lotteryId
	 * @return
	 */
	public int getLotteryStatus(int lotteryId){
		String SQL = "SELECT LotteryStatus FROM lottery_activity WHERE LotteryId = ?";
		int lotteryStatus = jdbcTemplate.queryForObject(SQL, new Object[]{lotteryId}, new LotteryStatusMapper());
		return lotteryStatus;
	}
	
	private static final class LotteryStatusMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
			Integer lotteryStatus = rs.getInt("LotteryStatus");
			return lotteryStatus;
		}	
	}
	
	/**
	 * @Title: getChanceNum
	 * @Description: 根据活动Id返回活动最大抽奖次数
	 * @param lotteryId
	 * @return
	 */
	public int getChanceNum(int lotteryId){
		String SQL = "SELECT ChanceNum FROM lottery_activity WHERE LotteryId = ?";
		int chanceNum = jdbcTemplate.queryForObject(SQL, new Object[]{lotteryId}, new ChanceNumMapper());
		return chanceNum;
	}
	
	private static final class ChanceNumMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
			Integer chanceNum = rs.getInt("ChanceNum");
			return chanceNum;
		}		
	}
	
	//private methods dealing with other models
	/**
	 * LotteryPrize Model
	 */
	private int insertPrize(LotteryPrize lPrize, int lotteryId){
		String SQL = "INSERT INTO lottery_prize(LotteryId, PrizeName, PrizeContent, LuckyNum, LuckyPercent) VALUES (?, ?, ?, ?, ?)";
		int prizeId = jdbcTemplate.update(SQL, lotteryId, lPrize.getPrizeName(),
				lPrize.getPrizeContent(), lPrize.getLuckyNum(), lPrize.getLuckyPercent());
		return prizeId;
	}
	
	private int deletePrize(int lotteryId){
		String SQL = "DELETE FROM lottery_prize WHERE LotteryId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, lotteryId);
		return effectedRowNum;
	}
	
	private List<LotteryPrize> getLotteryPrizeList(int lotteryId){
		String SQL = "SELECT * FROM lottery_prize WHERE LotteryId = ?";
		List<LotteryPrize> vList = jdbcTemplate.query(SQL, new Object[]{lotteryId}, new LotteryPrizeFullMapper());
		return vList;
	}
	
	private static final class LotteryPrizeFullMapper implements RowMapper<LotteryPrize> {
		@Override
		public LotteryPrize mapRow(ResultSet rs, int arg1) throws SQLException {
			LotteryPrize lPrize = new LotteryPrize();
			lPrize.setPrizeId(rs.getInt("PrizeId"));
			lPrize.setLotteryId(rs.getInt("LotteryId"));
			lPrize.setPrizeName(rs.getString("PrizeName"));
			lPrize.setPrizeContent(rs.getString("PrizeContent"));
			lPrize.setLuckyNum(rs.getInt("LuckyNum"));
			lPrize.setLuckyPercent(rs.getBigDecimal("LuckyPercent"));
			return lPrize;
		}		
	}
	
	private List<Integer> getPrizeIdList(int lotteryId){
		String SQL = "SELECT PrizeId FROM lottery_prize WHERE LotteryId = ?";
		List<Integer> iList = jdbcTemplate.query(SQL, new Object[]{lotteryId}, new PrizeIdMapper());
		return iList;
	}
	
	private static final class PrizeIdMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
			Integer prizeId = rs.getInt("PrizeId");
			return prizeId;
		}		
	}
	
	/**
	 * LotteryRecord Model
	 */
	private int deleteLotteryRecord(int lotteryId){
		String SQL = "DELETE FROM lottery_record WHERE LotteryId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, lotteryId);
		return effectedRowNum;
	}
	
	/**
	 * LuckyRecord Model
	 */
	private int deleteLuckyRecord(int prizeId){
		String SQL = "DELETE FROM lottery_lucky_record WHERE PrizeId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, prizeId);
		return effectedRowNum;
	}
}
