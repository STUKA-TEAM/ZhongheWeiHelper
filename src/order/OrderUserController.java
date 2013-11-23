package order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @Title: OrderController
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月20日
 */
@Controller
public class OrderUserController {

    @RequestMapping(value = "/user/menu", method = RequestMethod.GET)
    public @ResponseBody
    String getMenu(Model model) {
        UserManager manager = new UserManager();
        String jsonMenu = manager.TodayMenu();
        model.addAttribute("JsonMenu", jsonMenu);
        return "TodayMenu";
    }

    @RequestMapping(value = "/user/order", method = RequestMethod.POST)
    public @ResponseBody
    String PlaceOrder(@RequestBody String orderAndOpenId) {
        UserManager manager = new UserManager();
        Gson gson = new Gson();
        PlaceOrder order = gson.fromJson(orderAndOpenId,
                new TypeToken<PlaceOrder>() {
                }.getType());
        String openId = order.getOpenId();
        String jsonOrder = gson.toJson(order.getUserOrder());
        List<OrderUserOrder> userOrders = gson.fromJson(jsonOrder,
                new TypeToken<OrderUserOrder>() {
                }.getType());
        manager.PlaceOrder(openId, userOrders);
        return "OrderSuccess";
    }

    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public @ResponseBody
    String getOrder(
            @RequestParam(value = "openId", required = true) String openId, Model model) {
        UserManager manager = new UserManager();
        String myOrder = manager.getMyOrder(openId);
        model.addAttribute("myOrder", myOrder);
        return "UsersOrder";
    }

    @RequestMapping(value = "/user/wiki", method = RequestMethod.GET)
    public @ResponseBody
    String getWiki() {
        UserManager manager = new UserManager();
        String wiki = manager.getOrderWiki();
        return wiki;
    }

}
