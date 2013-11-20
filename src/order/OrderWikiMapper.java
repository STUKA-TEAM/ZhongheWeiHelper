package order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @Title: OrderWikiMapper
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月18日
 */
public class OrderWikiMapper implements RowMapper<OrderWiki>{

    @Override
    public OrderWiki mapRow(ResultSet rs, int arg1) throws SQLException {
        OrderWiki orderWiki = new OrderWiki();
        orderWiki.setWikiId(rs.getInt("WikiId"));
        orderWiki.setWiki(rs.getString("Wiki"));
        return orderWiki;
    }
}
