package order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @Title: OrderStoreMapper
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderStoreMapper implements RowMapper<OrderStore> {

    @Override
    public OrderStore mapRow(ResultSet rs, int arg1) throws SQLException {
        OrderStore orderStore = new OrderStore();
        orderStore.setStoreId(rs.getInt("StoreId"));
        orderStore.setStoreName(rs.getString("StoreName"));
        return orderStore;
    }

}
