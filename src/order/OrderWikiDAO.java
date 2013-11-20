package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @Title: OrderWikiDAO
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderWikiDAO {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Create
    /**
     * 
     * @param wiki
     * @return auto-generated key
     */
    public int createOrderWiki(final OrderWiki orderWiki) {
        final String SQL = "insert into order_wiki(Wiki) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS);
                System.out.println("HERE");
                ps.setString(1, orderWiki.getWiki());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // Delete
    /**
     * 
     * @param id
     * @return number of effected rows
     */
    public int deleteById(int id) {
        String SQL = "delete from order_wiki where WikiId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL, id);
        return effectedRowNum;
    }

    // Update
    /**
     * 
     * @param id
     * @param wiki
     * @return
     */
    public int updateById(OrderWiki orderWiki) {
        String SQL = "update order_wiki set Wiki = ? where WikiId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL,
                new Object[] { orderWiki.getWiki(), orderWiki.getWikiId() });
        return effectedRowNum;
    }

    // Query
    /**
     * 
     * @param id
     * @return the OrderWiki object
     */
    public OrderWiki getOrderWikiById(int id) {
        String SQL = "select * from order_wiki where WikiId = ?";
        OrderWiki orderWiki = jdbcTemplate.queryForObject(SQL,
                new Object[] { id }, new OrderWikiMapper());
        return orderWiki;
    }

    /**
     * 
     * @return the list of OrderWiki object
     */
    public List<OrderWiki> getOrderWikiList() {
        String SQL = "select * from order_wiki";
        List<OrderWiki> list = jdbcTemplate.query(SQL, new OrderWikiMapper());
        return list;
    }
}
