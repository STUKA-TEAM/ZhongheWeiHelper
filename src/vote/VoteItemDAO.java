package vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @Title: VoteItemDAO
 * @Description: DAO for VoteItem model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月20日
 */
public class VoteItemDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertItem
	 * @Description: 向数据库中插入投票选项记录
	 * @param vItem
	 * @return
	 */
	public int insertItem(final VoteItem vItem){
		int result = 0;
		final String SQL = "INSERT INTO vote_item(VoteId, ItemImage, ItemDesc) VALUES (?, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	            PreparedStatement ps =
	                connection.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
	            ps.setInt(1, vItem.getVoteId());
	            ps.setString(2, vItem.getItemImage());
	            ps.setString(3, vItem.getItemDesc());
	            return ps;
	        }
	    }, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	
	/**
	 * @Title: insertItemList
	 * @Description: 插入一组投票选项记录
	 * @param vList
	 * @return
	 */
	public List<Integer> insertItemList(List<VoteItem> vList){
		List<Integer> iList = new ArrayList<Integer>();
		
		for(int i = 0; i < vList.size(); i++){
			int temp = insertItem(vList.get(i));
			iList.add(temp);
		}
		
		return iList;
	}
	
	//delete
	/**
	 * @Title: deleteByVoteId
	 * @Description: 根据投票活动Id删除相关选项记录
	 * @param voteId
	 * @return
	 */
	public int deleteByVoteId(int voteId){
		String SQL = "DELETE FROM vote_item WHERE VoteId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, voteId);
		return effectedRowNum;
	}
	
	/**
	 * @Title: deleteByItemId
	 * @Description: 根据投票选项Id删除相关选项记录
	 * @param itemId
	 * @return
	 */
	public int deleteByItemId(int itemId){
		String SQL = "DELETE FROM vote_item WHERE ItemId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, itemId);
		return effectedRowNum;
	}
	
    //query
	/**
	 * @Title: getVoteItemList
	 * @Description: 根据投票活动Id获取投票选项列表
	 * @param voteId
	 * @return
	 */
	public List<VoteItem> getVoteItemList(int voteId){
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
	
	/**
	 * @Title: getItemIdList
	 * @Description: 根据投票活动Id获取投票选项Id列表
	 * @param voteId
	 * @return
	 */
	public List<Integer> getItemIdList(int voteId){
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
}
