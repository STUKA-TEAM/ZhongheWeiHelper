package order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @Title: OrderMenuMapper
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderMenuMapper implements RowMapper<OrderMenu> {

    @Override
    public OrderMenu mapRow(ResultSet rs, int arg1) throws SQLException {
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.setMenuId(rs.getInt("MenuId"));
        orderMenu.setDate(rs.getInt("Date"));
        orderMenu.setDish(rs.getString("Dish"));
        return orderMenu;
    }

}
