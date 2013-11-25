package order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @Title: OrderStoreController
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月20日
 */
@Controller
public class OrderStoreController {

    @RequestMapping(value = "/store/dish", method = RequestMethod.POST)
    public @ResponseBody
    String PostDish(@RequestBody String jsonDish) {
        Gson gson = new Gson();
        PostDish dish = gson.fromJson(jsonDish, new TypeToken<PostDish>() {
        }.getType());
        String opt = dish.getOperate();
        StoreManager manager = new StoreManager();
        if (opt.equals("add")) {
            manager.addDish(dish.getDishName(), dish.getPrice(),
                    dish.getPicture(), dish.getDecribe());
        } else if (opt.equals("add")) {
            OrderDish orderDish = new OrderDish();
            orderDish.setDishName(dish.getDishName());
            orderDish.setPrice(dish.getPrice());
            orderDish.setPicture(dish.getPicture());
            orderDish.setDiscribe(dish.getDecribe());
            manager.setDish(orderDish);
        } else {
            return "{result:error}";
        }
        return "{result:success}";
    }

    @RequestMapping(value = "/store/dish/del", method = RequestMethod.POST)
    public @ResponseBody
    String DelDish(@RequestBody String jsonDish) {
        Gson gson = new Gson();
        DelDish dish = gson.fromJson(jsonDish, new TypeToken<DelDish>() {
        }.getType());
        String opt = dish.getOperate();
        StoreManager manager = new StoreManager();
        if (opt.equals("delete")) {
            manager.deleteDish(dish.getDishId());
        } else {
            return "{result:error}";
        }
        return "{result:success}";
    }

    @RequestMapping(value = "/store/dishs", method = RequestMethod.GET)
    public String GetDish(Model model) {
        StoreManager manager = new StoreManager();
        List<OrderDish> dishes = manager.getDishes();
        model.addAttribute("DishList", dishes);
        return "DishManage";
    }

    @RequestMapping(value = "/store/menu", method = RequestMethod.POST)
    public @ResponseBody
    String PostMenu(@RequestBody String jsonMenu) {
        Gson gson = new Gson();
        PostMenu menu = gson.fromJson(jsonMenu, new TypeToken<PostMenu>() {
        }.getType());
        String opt = menu.getOperate();
        if (opt.equals("set")) {
            StoreManager manager = new StoreManager();
            OrderMenu orderMenu = new OrderMenu();
            orderMenu.setDate(menu.getDate());
            orderMenu.setDish(menu.getDishId());
            manager.setMenu(orderMenu);
        } else {
            return "{result:error}";
        }
        return "{result:success}";
    }

    @RequestMapping(value = "/store/menu/dish", method = RequestMethod.GET)
    public @ResponseBody
    String GetMenuDish() {
        StoreManager manager = new StoreManager();
        List<OrderMenu> menus = manager.getMenu();
        List<OrderMenu> menu4Json = new ArrayList<OrderMenu>();
        List<OrderDish> dishes = null;
        String jsonDishes = null;
        Gson gson = new Gson();
        for (OrderMenu menu : menus) {
            dishes = manager.getDishesByDate(menu.getDate());
            jsonDishes = gson.toJson(dishes);
            menu.setDish(jsonDishes);
            menu4Json.add(menu);
        }
        String jsonMenus = gson.toJson(menu4Json);
        return jsonMenus;
    }

    @RequestMapping(value = "/store/menu", method = RequestMethod.GET)
    public String GetMenuPage() {
        return "MenuManage";
    }

    @RequestMapping(value = "/store/order", method = RequestMethod.POST)
    public @ResponseBody
    String PostOrder(@RequestBody String jsonOrder) {
        StoreManager manager = new StoreManager();
        Gson gson = new Gson();
        OrderUserOrder userOrder = gson.fromJson(jsonOrder,
                new TypeToken<OrderUserOrder>() {
                }.getType());
        manager.setOrder(userOrder.getOrderId(), userOrder.getState());
        return "{result:success}";
    }

    @RequestMapping(value = "/store/order/today", method = RequestMethod.GET)
    public @ResponseBody
    String GetOrder() {
        StoreManager manager = new StoreManager();
        List<OrderUserOrder> userOrders = manager.getOrders();
        List<GetOrder> orders = new ArrayList<GetOrder>();
        GetOrder order = null;
        for (OrderUserOrder userOrder : userOrders){
            order.setOrderId(userOrder.getOrderId());
            order.setDate(userOrder.getDate());
            order.setPrice(userOrder.getPrice());
            order.setState(userOrder.getState());
            int userId = userOrder.getUserId();
            
            order.setUserName(userName);
            order.setAddress(address);
            order.setPhone(phone);
            
            order.setJsonDishes(jsonDishes);
        }
        return null;
    }

    @RequestMapping(value = "/store/order", method = RequestMethod.GET)
    public String GetOrderPage() {
        return "OrderManage";
    }

    @RequestMapping(value = "/store/wiki", method = RequestMethod.POST)
    public @ResponseBody
    String PostWiki(@RequestBody String jsonWiki) {
        Gson gson = new Gson();
        OrderWiki orderWiki = gson.fromJson(jsonWiki,
                new TypeToken<OrderWiki>() {
                }.getType());
        StoreManager manager = new StoreManager();
        manager.setOrderWiki(orderWiki);
        return "{result:success}";
    }

    @RequestMapping(value = "/store/wiki", method = RequestMethod.GET)
    public @ResponseBody
    String GetWiki() {
        StoreManager manager = new StoreManager();
        return manager.getOrderWiki();
    }
}
