package order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;

/**
 * @Title: UserOrderManager
 * @Description: 用户订餐接口
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月10日
 */
public class UserManager {

    private ApplicationContext mContext;

    public UserManager() {
        mContext = new ClassPathXmlApplicationContext("All-Modules.xml");
    }

    /**
     * 
     * @Title: TodayMenu
     * @Description: get today menu
     * @return JSON
     * @date 2013年11月20日
     */
    public String TodayMenu() {
        List<OrderDish> menu = new ArrayList<OrderDish>();

        OrderMenuDAO orderMenuDAO = (OrderMenuDAO) mContext
                .getBean("OrderMenuDAO");
        OrderMenu orderMenu = orderMenuDAO.getOrderMenuByDate(getWeekOfDate());
        String dish = orderMenu.getDish();
        String[] dishes = dish.split(";");
        OrderDishDAO orderDishDAO = (OrderDishDAO) mContext
                .getBean("OrderDishDAO");
        for (int i = 0; i < dishes.length; ++i) {
            OrderDish orderDish = orderDishDAO.getOrderDishById(Integer
                    .parseInt(dishes[i]));
            menu.add(orderDish);
        }
        Gson gson = new Gson();
        return gson.toJson(menu);
    }

    /**
     * 
     * @Title: getMenus
     * @Description: get all day menus
     * @return JSON
     * @date 2013年11月20日
     */
    public String getMenus() {
        OrderMenuDAO orderMenuDAO = (OrderMenuDAO) mContext
                .getBean("OrderMenuDAO");
        List<OrderMenu> orderMenus = orderMenuDAO.getOrderMenuList();
        Gson gson = new Gson();
        return gson.toJson(orderMenus);
    }

    /**
     * 
     * @Title: PlaceOrder
     * @Description: place order and insert into date base
     * @param openId
     * @param dishId
     * @param dishNum
     * @param price
     * @param state
     * @return today menu
     * @date 2013年11月20日
     */
    public void PlaceOrder(String openId, List<OrderUserOrder> userOrders) {
        OrderUserOrderDAO userOrderDAO = (OrderUserOrderDAO) mContext
                .getBean("OrderUserOrder");
        OrderUserDAO userDAO = (OrderUserDAO) mContext.getBean("OrderUserDAO");
        OrderUser user = userDAO.getOrderUserByOpenId(openId);
        Date date = getDate();
        for (OrderUserOrder userOrder : userOrders) {
            userOrder.setDate(date);
            userOrder.setUserId(user.getUserId());
            userOrderDAO.createOrderUserOrder(userOrder);
        }
    }

    /**
     * 
     * @Title: getMyOrder
     * @Description: get user's today order
     * @param openId
     * @return JSON
     * @date 2013年11月21日
     */
    public String getMyOrder(String openId) {
        OrderUserDAO userDAO = (OrderUserDAO) mContext.getBean("OrderUserDAO");
        OrderUser user = userDAO.getOrderUserByOpenId(openId);
        OrderUserOrderDAO userOrderDAO = (OrderUserOrderDAO) mContext
                .getBean("OrderUserOrderDAO");
        List<OrderUserOrder> userOrder = userOrderDAO.getOrderUserOrder(
                user.getUserId(), getDate());
        Gson gson = new Gson();
        return gson.toJson(userOrder);
    }

    /**
     * 
     * @Title: setOrderWiki
     * @Description: set order wiki
     * @param wiki
     * @date 2013年11月20日
     */
    public void setOrderWiki(String wiki) {
        OrderWikiDAO wikiDAO = (OrderWikiDAO) mContext.getBean("OrderWikiDAO");
        OrderWiki orderWiki = new OrderWiki();
        orderWiki.setWiki(wiki);
        wikiDAO.createOrderWiki(orderWiki);
    }

    /**
     * 
     * @Title: getOrderWiki
     * @Description: get order wiki
     * @return String
     * @date 2013年11月20日
     */
    public String getOrderWiki() {
        OrderWikiDAO wikiDAO = (OrderWikiDAO) mContext.getBean("OrderWikiDAO");
        List<OrderWiki> orderWikis = wikiDAO.getOrderWikiList();
        String wiki = orderWikis.get(0).getWiki();
        return wiki;
    }

    /**
     * 
     * @Title: getWeekOfDate
     * @Description: get today Week of date (e.g. 1 monday 2 tuesday)
     * @return int
     * @date 2013年11月20日
     */
    public static int getWeekOfDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 
     * @Title: getDate
     * @Description: get today date
     * @return Date
     * @date 2013年11月20日
     */
    public static Date getDate() {
        Calendar c = java.util.Calendar.getInstance();
        Date d = new Date(c.getTimeInMillis());
        return d;
    }
}
