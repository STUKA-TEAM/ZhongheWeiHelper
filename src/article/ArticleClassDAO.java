package article;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;



public class ArticleClassDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public ArticleClass getArticleClass(int articleClassId){
		String SQL = "SELECT * FROM article_class WHERE ArticleClassId = ?";
		ArticleClass article = jdbcTemplate.queryForObject(SQL, new Object[]{articleClassId}, new ArticleClassMapper());		
		return article;
	}
	
	private static final class ArticleClassMapper implements RowMapper<ArticleClass>{
		@Override
		public ArticleClass mapRow(ResultSet rs, int arg1) throws SQLException {
			ArticleClass articleClass = new ArticleClass();
			articleClass.setArticleClassId(rs.getInt("ArticleClassId"));
			articleClass.setArticleClassName(rs.getString("ArticleClassName"));
			articleClass.setArticleClassShowType(rs.getInt("ArticleClassShowType"));
			return articleClass;
		}   	
	}
}
