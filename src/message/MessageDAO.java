package message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

public class MessageDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int insertMessage(final Message message){
		int result = 0;
		final String SQL = "insert message_record values(default,?,?,?,?)";
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, message.getContent());
				ps.setString(2, message.getUserName());
				ps.setString(3, message.getContact());
				ps.setTimestamp(4, message.getDate());
				return ps;
			}
		}, kHolder);		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
}
