package weather;

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
import com.sun.istack.internal.FinalArrayList;

public class WeatherCityDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//insert
	public int insertCity(final String openId, final String city){
		
		String getSQL = "select * from weather_city where UserOpenId = ?";
		List<SavedCity> savedCities = jdbcTemplate.query(getSQL, 
				new Object[]{openId}, new SavedCityMapper());
		if(savedCities.contains(city)){
			return 0;
		}
		
		
		int result = 0;
		final String insertSQL = "INSERT INTO weather_city VALUES(default, ?, ?)";
		
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, openId);
				ps.setString(2, city);
				return ps;
			}
		}, kHolder);
		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
	//getList
	public List<SavedCity> getSavedCityListByOpenId(String openId){
		String SQL = "select * from weather_city where UserOpenId = ?";
		List<SavedCity> savedCities = jdbcTemplate.query(SQL, 
				new Object[]{openId}, new SavedCityMapper());
		return savedCities;
	}
	
    private static final class SavedCityMapper implements RowMapper<SavedCity>{
		@Override
		public SavedCity mapRow(ResultSet rs, int arg1) throws SQLException {
			SavedCity savedCity = new SavedCity();
			savedCity.setOpenId(rs.getString("UserOpenID"));
			savedCity.setCity(rs.getString("City"));
			return savedCity;
		}   	
    }
}
