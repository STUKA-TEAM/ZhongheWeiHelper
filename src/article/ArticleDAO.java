package article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import message.Message;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

public class ArticleDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int insertArticle(final Article article){
		int result = 0;
		final String SQL = "insert article_record values(default,?,?,?,?,?,?)";
		KeyHolder kHolder = new GeneratedKeyHolder();
		
		result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = 
						connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, article.getTitle());
				ps.setTimestamp(2, article.getDate());
				ps.setString(3, article.getImagePath());
				ps.setString(4, article.getContent());
				ps.setInt(5, article.getListClass());
				ps.setInt(6, article.getArticleClassId());
				return ps;
			}
		}, kHolder);		
		return result == 0 ? result : kHolder.getKey().intValue();
	}
}
