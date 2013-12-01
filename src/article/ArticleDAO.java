package article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import weather.SavedCity;

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
	
	public Article getArticle(int articleId){
		String SQL = "SELECT * FROM article_record WHERE ArticleId = ?";
		Article article = jdbcTemplate.queryForObject(SQL, new Object[]{articleId}, new ArticleMapper());		
		return article;
	}
	
	private static final class ArticleMapper implements RowMapper<Article>{
		@Override
		public Article mapRow(ResultSet rs, int arg1) throws SQLException {
			Article article = new Article();
			article.setArticleId(rs.getInt("ArticleId"));
			article.setTitle(rs.getString("Title"));
			article.setDate(rs.getTimestamp("Date"));
			article.setImagePath(rs.getString("ImagePath"));
			article.setContent(rs.getString("Content"));
			article.setListClass(rs.getInt("ListClass"));
			article.setArticleClassId(rs.getInt("ArticleClassId"));
			return article;
		}   	
}
}
