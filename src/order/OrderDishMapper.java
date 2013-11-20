package order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderDishMapper implements RowMapper<OrderDish> {

    @Override
    public OrderDish mapRow(ResultSet rs, int arg1) throws SQLException {
        OrderDish orderDish = new OrderDish();
        orderDish.setDishId(rs.getInt("DishId"));
        orderDish.setDishName(rs.getString("DishName"));
        orderDish.setPrice(rs.getBigDecimal("Price"));
        orderDish.setDiscribe(rs.getString("Discribe"));
        orderDish.setPicture(rs.getString("Picture"));
        return orderDish;
    }

}
