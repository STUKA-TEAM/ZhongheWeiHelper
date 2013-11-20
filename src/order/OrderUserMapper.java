package order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @Title: OrderUserMapper
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderUserMapper implements RowMapper<OrderUser>{

    @Override
    public OrderUser mapRow(ResultSet rs, int arg1) throws SQLException {
        OrderUser orderUser = new OrderUser();
        orderUser.setUserId(rs.getInt("UserId"));
        orderUser.setOpenId(rs.getString("OpenId"));
        orderUser.setAddress(rs.getString("Address"));
        orderUser.setPhone(rs.getString("Phone"));
        return orderUser;
    }
}
