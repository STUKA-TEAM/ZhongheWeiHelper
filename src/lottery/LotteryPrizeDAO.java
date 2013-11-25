package lottery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @Title: LotteryPrizeDAO
 * @Description: DAO for LotteryPrize model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月23日
 */
public class LotteryPrizeDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertPrize
	 * @Description: 向数据库中插入奖项记录
	 * @param lPrize
	 * @return
	 */
	public int insertPrize(final LotteryPrize lPrize){
		int result = 0;
		final String SQL = "INSERT INTO lottery_prize(LotteryId, PrizeName, "
				+ "PrizeContent, LuckyNum, LuckyPercent) VALUES (?, ?, ?, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement ps =
	                connection.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
	            ps.setInt(1, lPrize.getLotteryId());
	            ps.setString(2, lPrize.getPrizeContent());
	            ps.setString(3, lPrize.getPrizeContent());
	            ps.setInt(4, lPrize.getLuckyNum());
	            ps.setBigDecimal(5, lPrize.getLuckyPercent());
	            return ps;
	        }
	    }, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	
	/**
	 * @Title: insertPrizeList
	 * @Description: 插入一组奖项记录
	 * @param pList
	 * @return
	 */
	public List<Integer> insertPrizeList(List<LotteryPrize> pList){
		List<Integer> iList = new ArrayList<Integer>();
		
		/**按中奖率由小到大排序*/
		Collections.sort(pList, new Comparator<LotteryPrize>() {
			@Override
			public int compare(LotteryPrize o1, LotteryPrize o2) {
				return o1.getLuckyPercent().compareTo(o2.getLuckyPercent());
			}
		});
		
		for(int i = 0; i < pList.size(); i++){
			int temp = insertPrize(pList.get(i));
			iList.add(temp);
		}
		
		return iList;
	}
	
	//delete
	/**
	 * @Title: deleteByLotteryId
	 * @Description: 根据抽奖活动Id删除相关选项记录
	 * @param lotteryId
	 * @return
	 */
	public int deleteByLotteryId(int lotteryId){
		String SQL = "DELETE FROM lottery_prize WHERE LotteryId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, lotteryId);
		return effectedRowNum;
	}
	
	/**
	 * @Title: deleteByPrizeId
	 * @Description: 根据奖项项Id删除相关选项记录
	 * @param prizeId
	 * @return
	 */
	public int deleteByPrizeId(int prizeId){
		String SQL = "DELETE FROM lottery_prize WHERE PrizeId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, prizeId);
		return effectedRowNum;
	}
	
    //query
	/**
	 * @Title: getLotteryPrizeList
	 * @Description: 根据抽奖活动Id获取对应奖项列表
	 * @param voteId
	 * @return
	 */
	public List<LotteryPrize> getLotteryPrizeList(int lotteryId){
		String SQL = "SELECT * FROM lottery_prize WHERE LotteryId = ?";
		List<LotteryPrize> pList = jdbcTemplate.query(SQL, new Object[]{lotteryId}, new LotteryPrizeFullMapper());
		return pList;
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
	
	/**
	 * @Title: getPrizeIdList
	 * @Description: 根据抽奖活动Id获取对应奖项Id列表
	 * @param lotteryId
	 * @return
	 */
	public List<Integer> getPrizeIdList(int lotteryId){
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
}
