package vote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

/**
 * @Title: ChoiceDAO
 * @Description: DAO for Choice model
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月20日
 */
public class ChoiceDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	/**
	 * @Title: insertChoice
	 * @Description: 插入一条用户选择记录
	 * @param choice
	 * @return
	 */
	public int insertChoice(final Choice choice){
		int result = 0;
		final String SQL = "INSERT INTO vote_item_choice(ItemId, OpenId) VALUES (?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, choice.getItemId());
				ps.setString(2, choice.getOpenId());
				return ps;
			}
		}, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue(); 
	}
	
	/**
	 * @Title: insertChoiceList
	 * @Description: 插入一组用户选择记录
	 * @param cList
	 * @return
	 */
	public List<Integer> insertChoiceList(List<Choice> cList){
		List<Integer> iList = new ArrayList<Integer>();
		
		for(int i = 0; i < cList.size(); i++){
			int temp = insertChoice(cList.get(i));
			iList.add(temp);
		}
		
		return iList;
	}
	
	//delete
	/**
	 * @Title: deleteByItemId
	 * @Description: 根据投票选项Id删除相关的用户投票记录
	 * @param itemId
	 * @return
	 */
	public int deleteByItemId(int itemId){
		String SQL = "DELETE FROM vote_item_choice WHERE ItemId = ?";
		int effectedRowNum = jdbcTemplate.update(SQL, itemId);
		return effectedRowNum;
	}
	
	//query
	/**
	 * @Title: getChoiceList
	 * @Description: 查找一条投票选项对应的选择记录
	 * @param itemId
	 * @return
	 */
	public List<Choice> getChoiceList(int itemId){
		String SQL = "SELECT * FROM vote_item_choice WHERE ItemId = ?";
		List<Choice> cList = jdbcTemplate.query(SQL, new Object[]{itemId}, new ChoiceFullMapper());
		return cList;
	}
	
	private static final class ChoiceFullMapper implements RowMapper<Choice> {
		@Override
		public Choice mapRow(ResultSet rs, int arg1) throws SQLException {
			Choice choice = new Choice();
			choice.setChoiceId(rs.getInt("ChoiceId"));
			choice.setItemId(rs.getInt("ItemId"));
			choice.setOpenId(rs.getString("OpenId"));
			return choice;
		}		
	}
	
	/**
	 * @Title: getChoiceNum
	 * @Description: 查找与指定投票选项相关的用户选择记录数
	 * @param itemId
	 * @return
	 */
	public int getChoiceNum(int itemId){
		String SQL = "SELECT COUNT(*) FROM vote_item_choice WHERE ItemId = ?";
		int result = jdbcTemplate.queryForObject(SQL, Integer.class, itemId);
		return result;
	}
}
