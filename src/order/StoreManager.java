package order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;

/**
 * @Title: StoreOrderManager
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月10日
 */
public class StoreManager {

    private ApplicationContext mContext;

    public StoreManager() {
        mContext = new ClassPathXmlApplicationContext("All-Modules.xml");
    }

    public void addDish(String dishName, BigDecimal price, String discribe,
            String picture) {
        OrderDish orderDish = new OrderDish();
        orderDish.setDishName(dishName);
        orderDish.setDiscribe(discribe);
        orderDish.setPrice(price);
        orderDish.setPicture(picture);
        OrderDishDAO orderDishDAO = (OrderDishDAO) mContext
                .getBean("OrderDishDAO");
        orderDishDAO.createOrderDish(orderDish);
    }

    public void setDish(OrderDish orderDish) {
        OrderDishDAO orderDishDAO = (OrderDishDAO) mContext
                .getBean("OrderDishDAO");
        orderDishDAO.updateById(orderDish);
    }

    public void deleteDish(int id) {
        OrderDishDAO orderDishDAO = (OrderDishDAO) mContext
                .getBean("OrderDishDAO");
        orderDishDAO.deleteById(id);
    }

    public String getDishes() {
        OrderDishDAO orderDishDAO = (OrderDishDAO) mContext
                .getBean("OrderDishDAO");
        List<OrderDish> orderDishes = orderDishDAO.getOrderDishList();
        Gson gson = new Gson();
        return gson.toJson(orderDishes);
    }

    public String getMenu() {
        OrderMenuDAO orderMenuDAO = (OrderMenuDAO) mContext
                .getBean("OrderMenuDAO");
        List<OrderMenu> orderMenus = orderMenuDAO.getOrderMenuList();
        Gson gson = new Gson();
        return gson.toJson(orderMenus);
    }

    public void addMenu(int date, List<String> dishNames) {
        OrderDishDAO orderDishDAO = (OrderDishDAO) mContext
                .getBean("OrderDishDAO");
        String dish = "";
        for (String dishName : dishNames) {
            OrderDish orderDish = orderDishDAO.getOrderDishByName(dishName);
            dish += orderDish.getDishId() + ";";
        }
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.setDish(dish);
        orderMenu.setDate(date);
        OrderMenuDAO orderMenuDAO = (OrderMenuDAO) mContext
                .getBean("OrderMenuDAO");
        orderMenuDAO.createOrderMenu(orderMenu);
    }

    public void setMenu(OrderMenu orderMenu) {
        OrderMenuDAO orderMenuDAO = (OrderMenuDAO) mContext
                .getBean("OrderMenuDAO");
        orderMenuDAO.updateByDate(orderMenu);
    }

    public void setOrder(OrderUserOrder userOrder) {
        OrderUserOrderDAO userOrderDAO = (OrderUserOrderDAO) mContext
                .getBean("OrderUserOrderDAO");
        userOrderDAO.updateById(userOrder);
    }

    public String getOrders() {
        OrderUserOrderDAO userOrderDAO = (OrderUserOrderDAO) mContext
                .getBean("OrderUserOrderDAO");
        List<OrderUserOrder> userOrders = userOrderDAO.getOrderUserOrderList();
        Gson gson = new Gson();
        return gson.toJson(userOrders);
    }

    public void addOrderWiki(String wiki) {
        OrderWiki orderWiki = new OrderWiki();
        orderWiki.setWiki(wiki);
        OrderWikiDAO orderWikiDAO = (OrderWikiDAO) mContext
                .getBean("OrderWikiDAO");
        orderWikiDAO.createOrderWiki(orderWiki);
    }

    public void setOrderWiki(OrderWiki orderWiki) {
        OrderWikiDAO orderWikiDAO = (OrderWikiDAO) mContext
                .getBean("OrderWikiDAO");
        orderWikiDAO.updateById(orderWiki);
    }

    public String getOrderWiki() {
        OrderWikiDAO orderWikiDAO = (OrderWikiDAO) mContext
                .getBean("OrderWikiDAO");
        List<OrderWiki> orderWikis = orderWikiDAO.getOrderWikiList();
        Gson gson = new Gson();
        return gson.toJson(orderWikis);
    }
}
