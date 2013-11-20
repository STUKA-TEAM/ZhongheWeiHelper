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
 * @Title: OrderUserDAO
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderUserDAO {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Create
    /**
     * 
     * @param openId
     * @param adress
     * @param phone
     * @return auto-generated key
     */
    public int createOrderUser(final OrderUser orderUser) {
        final String SQL = "insert into order_user(OpenId, Address, Phone)"
                + " values (\"?\",\"?\",\"?\")";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS);
                System.out.println("HERE");
                ps.setString(1, orderUser.getOpenId());
                ps.setString(2, orderUser.getAddress());
                ps.setString(3, orderUser.getPhone());
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
        String SQL = "delete from order_user where UserId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL, id);
        return effectedRowNum;
    }

    // Update
    /**
     * 
     * @param id
     * @param openId
     * @param address
     * @param phone
     * @return
     */
    public int updateById(int id, String openId, String address, String phone) {
        String SQL = "update order_user set OpenId = \"?\", Address = \"?\","
                + "Phone = \"?\" where UserId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL, new Object[] { openId,
                address, phone });
        return effectedRowNum;
    }

    // Query
    /**
     * 
     * @param id
     * @return the OrderUser object
     */
    public OrderUser getOrderUserById(int id) {
        String SQL = "select * from order_user where UserId = ?";
        OrderUser orderUser = jdbcTemplate.queryForObject(SQL,
                new Object[] { id }, new OrderUserMapper());
        return orderUser;
    }
    
    // Query
    /**
     * 
     * @param openId
     * @return the OrderUser object
     */
    public OrderUser getOrderUserByOpenId(String openId) {
        String SQL = "select * from order_user where OpenId = \"?\"";
        OrderUser orderUser = jdbcTemplate.queryForObject(SQL,
                new Object[] { openId }, new OrderUserMapper());
        return orderUser;
    }

    /**
     * 
     * @return the list of OrderUser object
     */
    public List<OrderUser> getOrderUserList() {
        String SQL = "select * from order_user";
        List<OrderUser> list = jdbcTemplate.query(SQL, new OrderUserMapper());
        return list;
    }
}
