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
 * @Title: OrderStoreDAO
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderStoreDAO {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Create
    /**
     * 
     * @param storeId
     * @param storeName
     * @return auto-generated key
     */
    public int createOrderStore(final OrderStore orderStore) {
        final String SQL = "insert into order_store(StoreId, StoreName)"
                + " values (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS);
                System.out.println("HERE");
                ps.setInt(1, orderStore.getStoreId());
                ps.setString(2, orderStore.getStoreName());
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
        String SQL = "delete from order_store where StoreId = ?";
        int effectedRowNum = jdbcTemplate.update(SQL, id);
        return effectedRowNum;
    }

    // Update
    /**
     * 
     * @param id
     * @param name
     * @return
     */
    public int updateById(int id, String name) {
        String SQL = "update order_store set OrderName = ? where OrderId = ?";
        int effectedRowNum = jdbcTemplate
                .update(SQL, new Object[] { name, id });
        return effectedRowNum;
    }

    // Query
    /**
     * 
     * @param id
     * @return the OrderDish object
     */
    public OrderStore getOrderStoreById(int id) {
        String SQL = "select * from order_store where StoreId = ?";
        OrderStore orderStore = jdbcTemplate.queryForObject(SQL,
                new Object[] { id }, new OrderStoreMapper());
        return orderStore;
    }

    /**
     * 
     * @return the list of OrderDish object
     */
    public List<OrderStore> getOrderStoreList() {
        String SQL = "select * from order_store";
        List<OrderStore> list = jdbcTemplate.query(SQL, new OrderStoreMapper());
        return list;
    }
}
