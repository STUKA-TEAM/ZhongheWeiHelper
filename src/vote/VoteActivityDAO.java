package vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	 * @Description: 向数据库中插入一条活动记录以及相关的选项信息记录
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
		
		if (result > 0 && vActivity.getViList() != null) {    //insert items
			int voteId = kHolder.getKey().intValue();
			
			for (int i = 0; i < vActivity.getViList().size(); i++) {
				VoteItem vItem = vActivity.getViList().get(i);
				insertItem(vItem, voteId);			
			}
		}
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	
	//update
	/**
	 * @Title: updateActivity
	 * @Description: 更新投票活动信息记录以及关联记录
	 * @param vActivity
	 * @return
	 */
	public int updateActivity(VoteActivity vActivity){
		String SQL = "UPDATE vote_activity SET VoteTitle = ?, VoteSummary = ?, "
				+ "VotePicture = ?, StartDate = ?, EndDate = ?, IsMultiChoice = ?, "
				+ "MaxChoice = ?, EnableAdvice = ?, VoteStatus = ? WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, new Object[]{vActivity.getVoteTitle(), 
				vActivity.getVoteSummary(), vActivity.getVotePicture(), vActivity.getStartDate(),
				vActivity.getEndDate(), vActivity.getIsMultiChoice(), vActivity.getMaxChoice(), 
				vActivity.getEnableAdvice(), vActivity.getVoteStatus(), vActivity.getVoteId()});
		
		if (effectedRowNum > 0) {  //delete old items and insert new items
			deleteItem(vActivity.getVoteId());
			
			for (int i = 0; i < vActivity.getViList().size(); i++) {
				VoteItem vItem = vActivity.getViList().get(i);
				insertItem(vItem, vActivity.getVoteId());			
			}
		}
		return effectedRowNum;
	}
	
	/**
	 * @Title: updateStatus
	 * @Description: 改变指定活动Id的活动状态
	 * @param voteId
	 * @param voteStatus
	 * @return
	 */
	public int updateStatus(int voteId, int voteStatus){
		String SQL = "UPDATE vote_activity SET VoteStatus = ? WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, voteStatus, voteId);
		return effectedRowNum;
	}
	
    /**
     * @Title: updateStartDateAndStatus
     * @Description: 用于发布活动
     * @param voteId
     * @param startDate
     * @param voteStatus
     * @return
     */
	public int updateStartDateAndStatus(int voteId, Timestamp startDate, int voteStatus){
		String SQL = "UPDATE vote_activity SET StartDate = ?, VoteStatus = ? WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, startDate, voteStatus, voteId);
		return effectedRowNum;
	}
	
	/**
	 * @Title: updateEndDateAndStatus
	 * @Description: 用于关闭活动
	 * @param voteId
	 * @param endDate
	 * @param voteStatus
	 * @return
	 */
	public int updateEndDateAndStatus(int voteId, Timestamp endDate, int voteStatus){
		String SQL = "UPDATE vote_activity SET EndDate = ?, VoteStatus = ? WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, endDate, voteStatus, voteId);
		return effectedRowNum;
	}
	
	//delete
	/**
	 * @Title: deleteByVoteId
	 * @Description: 根据活动Id删除活动记录以及与之相关的一切信息记录
	 * @param voteId
	 * @return
	 */
	public int deleteByVoteId(int voteId){
		String SQL = "DELETE FROM vote_activity WHERE VoteId = ?";
		int effectedRowNum = 0;
		
		int voteStatus = checkVoteStatus(voteId);
		switch (voteStatus) {
		case Constant.ACTIVITY_DRAFT_STATUS:
		case Constant.ACTIVITY_SAVE_STATUS:
			deleteItem(voteId);
			effectedRowNum = jdbcTemplate.update(SQL, voteId);
			break;
		case Constant.ACTIVITY_CLOSED_STATUS:
			List<Integer> iList = getItemIdList(voteId);
			deleteItem(voteId);
			for (int i = 0; i < iList.size(); i++) {
				deleteChoice(iList.get(i));
			}
			int enableAdvice = checkEnableAdvice(voteId);
			if (enableAdvice == 1) {
				deleteAdvice(voteId);
			}
			effectedRowNum = jdbcTemplate.update(SQL, voteId);
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
	 * @param voteId
	 * @return
	 */
	public VoteActivity getActivity(int voteId){
		String SQL = "SELECT * FROM vote_activity WHERE VoteId = ?";
		VoteActivity vActivity = jdbcTemplate.queryForObject(SQL, new Object[]{voteId}, new VoteActivityFullMapper());
		
		List<VoteItem> vList = getVoteItemList(voteId); 
		vActivity.setViList(vList);
		
		return vActivity;
	}
	
	/**
	 * @Title: getActivityListByStatus
	 * @Description: 根据活动状态获取一组活动记录信息
	 * @param voteStatus
	 * @return
	 */
	public List<VoteActivity> getActivityListByStatus(int voteStatus){
		String SQL = "SELECT * FROM vote_activity WHERE VoteStatus = ?";
		List<VoteActivity> vList = jdbcTemplate.query(SQL, new Object[]{voteStatus}, new VoteActivityFullMapper());
		
		for (int i = 0; i < vList.size(); i++) {
			VoteActivity vActivity = vList.get(i);
			vActivity.setViList(getVoteItemList(vActivity.getVoteId()));
		}
		
		return vList;
	}
	
	/**
	 * @Title: getActivityList
	 * @Description: 获取数据库中所有活动配置信息记录
	 * @return
	 */
	public List<VoteActivity> getActivityList(){
		String SQL = "SELECT * FROM vote_activity";
		List<VoteActivity> vList = jdbcTemplate.query(SQL, new VoteActivityFullMapper());
		
		for (int i = 0; i < vList.size(); i++) {
			VoteActivity vActivity = vList.get(i);
			vActivity.setViList(getVoteItemList(vActivity.getVoteId()));
		}
		
		return vList;
	}
	
	private static final class VoteActivityFullMapper implements RowMapper<VoteActivity> {
		@Override
		public VoteActivity mapRow(ResultSet rs, int arg1)
				throws SQLException {
			VoteActivity vActivity = new VoteActivity();
			vActivity.setVoteId(rs.getInt("VoteId"));
			vActivity.setVoteTitle(rs.getString("VoteTitle"));
			vActivity.setVoteSummary(rs.getString("VoteSummary"));
			vActivity.setVotePicture(rs.getString("VotePicture"));
			vActivity.setStartDate(rs.getTimestamp("StartDate"));
			vActivity.setEndDate(rs.getTimestamp("EndDate"));
			vActivity.setIsMultiChoice(rs.getInt("IsMultiChoice"));
			vActivity.setMaxChoice(rs.getInt("MaxChoice"));
			vActivity.setEnableAdvice(rs.getInt("EnableAdvice"));
			vActivity.setVoteStatus(rs.getInt("VoteStatus"));
			return vActivity;
		}		
	}
	
	/**
	 * @Title: checkEnableAdvice
	 * @Description: 根据活动Id返回当前活动是否可留言信息
	 * @param voteId
	 * @return
	 */
	public int checkEnableAdvice(int voteId){
		String SQL = "SELECT EnableAdvice FROM vote_activity WHERE VoteId = ?";
		int enableAdvice = jdbcTemplate.queryForObject(SQL, new Object[]{voteId}, new EnableAdviceMapper());
		return enableAdvice;
	}
	
	private static final class EnableAdviceMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
			Integer enableAdvice = rs.getInt("EnableAdvice");
			return enableAdvice;
		}		
	}
	
	/**
	 * @Title: checkVoteStatus
	 * @Description: 根据活动Id返回活动当前状态
	 * @param voteId
	 * @return
	 */
	public int checkVoteStatus(int voteId){
		String SQL = "SELECT VoteStatus FROM vote_activity WHERE VoteId = ?";
		int voteStatus = jdbcTemplate.queryForObject(SQL, new Object[]{voteId}, new VoteStatusMapper());
		return voteStatus;
	}
	
	private static final class VoteStatusMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
			Integer voteStatus = rs.getInt("VoteStatus");
			return voteStatus;
		}	
	}
	
	//private methods dealing with other models
	/**
	 * VoteItem Model
	 */
	private int insertItem(VoteItem vItem, int voteId){
		String SQL = "INSERT INTO vote_item(VoteId, ItemImage, ItemDesc) VALUES (?, ?, ?)";
		int effectedRowNum = jdbcTemplate.update(SQL, voteId, vItem.getItemImage(), vItem.getItemDesc());
		return effectedRowNum;
	}
	
	private int deleteItem(int voteId){
		String SQL = "DELETE FROM vote_item WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, voteId);
		return effectedRowNum;
	}
	
	private List<VoteItem> getVoteItemList(int voteId){
		String SQL = "SELECT * FROM vote_item WHERE VoteId = ?";
		List<VoteItem> vList = jdbcTemplate.query(SQL, new Object[]{voteId}, new VoteItemFullMapper());
		return vList;
	}
	
	private static final class VoteItemFullMapper implements RowMapper<VoteItem> {
		@Override
		public VoteItem mapRow(ResultSet rs, int arg1) throws SQLException {
			VoteItem vItem = new VoteItem();
			vItem.setItemId(rs.getInt("ItemId"));
			vItem.setVoteId(rs.getInt("VoteId"));
			vItem.setItemImage(rs.getString("ItemImage"));
			vItem.setItemDesc(rs.getString("ItemDesc"));
			return vItem;
		}		
	}
	
	private List<Integer> getItemIdList(int voteId){
		String SQL = "SELECT ItemId FROM vote_item WHERE VoteId = ?";
		List<Integer> iList = jdbcTemplate.query(SQL, new Object[]{voteId}, new VoteItemIdMapper());
		return iList;
	}
	
	private static final class VoteItemIdMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
			Integer itemId = rs.getInt("ItemId");
			return itemId;
		}		
	}
	
	/**
	 * Choice Model
	 */
	private int deleteChoice(int itemId){
		String SQL = "DELETE FROM vote_item_choice WHERE ItemId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, itemId);
		return effectedRowNum;
	}
	
	/**
	 * Advice Model
	 */
	private int deleteAdvice(int voteId){
		String SQL = "DELETE FROM vote_advice WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, voteId);
		return effectedRowNum;
	}
}
