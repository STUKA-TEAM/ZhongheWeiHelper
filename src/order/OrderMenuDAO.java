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
 * @Title: OrderMenuDAO
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderMenuDAO {
    private JdbcTemplate jdbcTemplate;

    /**
     * @param dataSource
     *            the dataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Create
    /**
     * 
     * @param menuId
     * @param date
     * @param dish
     * @return auto-generated key
     */
    public int createOrderMenu(final OrderMenu orderMenu) {
        final String SQL = "insert into order_menu(MenuId, Date, "
                + "Dish) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS);
                System.out.println("HERE");
                ps.setInt(1, orderMenu.getMenuId());
                ps.setInt(2, orderMenu.getDate());
                ps.setString(3, orderMenu.getDish());

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
        String SQL = "delete from order_menu where MenuId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL, id);
        return effectedRowNum;
    }

    // Update
    /**
     * 
     * @param id
     * @param date
     * @param dish
     * @return
     */
    public int updateById(OrderMenu orderMenu) {
        String SQL = "update order_menu set Date = ?, Dish = ?"
                + " where MenuId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL,
                new Object[] { orderMenu.getDate(), orderMenu.getDish(),
                        orderMenu.getMenuId() });
        return effectedRowNum;
    }

    // Query
    /**
     * 
     * @param id
     * @return the OrderMenu object
     */
    public OrderMenu getOrderMenuById(int id) {
        String SQL = "select * from order_menu where MenuId = ?";
        OrderMenu orderMenu = jdbcTemplate.queryForObject(SQL,
                new Object[] { id }, new OrderMenuMapper());
        return orderMenu;
    }

    // Delete
    /**
     * 
     * @param date
     * @return number of effected rows
     */
    public int deleteByDate(int date) {
        String SQL = "delete from order_menu where Date = ?";
        int effectedRowNum = jdbcTemplate.update(SQL, date);
        return effectedRowNum;
    }

    // Update
    /**
     * 
     * @param date
     * @param dish
     * @return
     */
    public int updateByDate(OrderMenu orderMenu) {
        String SQL = "update order_menu set Dish = ? where Date = ?";
        int effectedRowNum = jdbcTemplate.update(SQL,
                new Object[] { orderMenu.getDish(), orderMenu.getDate() });
        return effectedRowNum;
    }

    // Query
    /**
     * 
     * @param id
     * @return the OrderMenu object
     */
    public OrderMenu getOrderMenuByDate(int date) {
        String SQL = "select * from order_menu where Date = ?";
        OrderMenu orderMenu = jdbcTemplate.queryForObject(SQL,
                new Object[] { date }, new OrderMenuMapper());
        return orderMenu;
    }

    /**
     * 
     * @return the list of OrderMenu object
     */
    public List<OrderMenu> getOrderMenuList() {
        String SQL = "select * from order_menu";
        List<OrderMenu> list = jdbcTemplate.query(SQL, new OrderMenuMapper());
        return list;
    }

}
