package order;

import java.sql.Connection;
import java.sql.Date;
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
 * @Title: OrderUserOrderDAO
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderUserOrderDAO {
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
    public int createOrderUserOrder(final OrderUserOrder orderUserOrder) {
        final String SQL = "insert into order_user_order(UserId, Date, DishId,"
                + " DishNum, Price, State) values (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS);
                System.out.println("HERE");
                ps.setInt(1, orderUserOrder.getUserId());
                ps.setDate(2, orderUserOrder.getDate());
                ps.setInt(3, orderUserOrder.getDishId());
                ps.setInt(4, orderUserOrder.getDishNum());
                ps.setBigDecimal(5, orderUserOrder.getPrice());
                ps.setInt(6, orderUserOrder.getState());
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
        String SQL = "delete from order_user_order where OrderId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL, id);
        return effectedRowNum;
    }

    // Update
    /**
     * 
     * @param id
     * @param UserId
     * @param date
     * @param dishId
     * @param dishNum
     * @param price
     * @param state
     * @return
     */
    public int updateById(OrderUserOrder userOrder) {
        String SQL = "update order_user_order set UserId = ?, Date = ?, DishId = ?, DishNum = ?, Price = ?, State = ? where OrderId = ?";
        int effectedRowNum = jdbcTemplate.update(
                SQL,
                new Object[] { userOrder.getUserId(), userOrder.getDate(),
                        userOrder.getDishId(), userOrder.getDishNum(),
                        userOrder.getPrice(), userOrder.getState(),
                        userOrder.getOrderId() });
        return effectedRowNum;
    }

    // Query
    /**
     * 
     * @param userId
     * @param date
     * @return the OrderUserOrder object
     */
    public List<OrderUserOrder> getOrderUserOrder(int userId, Date date) {
        String SQL = "select * from order_user_order where UserId = ? and"
                + " Date = ?";
        List<OrderUserOrder> orderUserOrders = jdbcTemplate.query(SQL,
                new Object[] { userId, date }, new OrderUserOrderMapper());
        return orderUserOrders;
    }

    /**
     * 
     * @return the list of OrderUserOrder object
     */
    public List<OrderUserOrder> getOrderUserOrderList() {
        String SQL = "select * from order_user_order";
        List<OrderUserOrder> list = jdbcTemplate.query(SQL,
                new OrderUserOrderMapper());
        return list;
    }
}
