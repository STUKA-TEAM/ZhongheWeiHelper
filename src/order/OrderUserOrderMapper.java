package order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @Title: OrderUserOrderMapper
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderUserOrderMapper implements RowMapper<OrderUserOrder>{

    @Override
    public OrderUserOrder mapRow(ResultSet rs, int arg1) throws SQLException {
        OrderUserOrder orderUserOrder = new OrderUserOrder();
        orderUserOrder.setOrderId(rs.getInt("OrderId"));
        orderUserOrder.setUserId(rs.getInt("UserId"));
        orderUserOrder.setDate(rs.getDate("Date"));
        orderUserOrder.setDishId(rs.getInt("DishId"));
        orderUserOrder.setDishNum(rs.getInt("DishNum"));
        orderUserOrder.setPrice(rs.getBigDecimal("Price"));
        orderUserOrder.setState(rs.getInt("State"));
        return orderUserOrder;
    }
}
