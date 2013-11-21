package order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String getMenu() {
        return null;
    }

    @RequestMapping(value = "/user/order", method = RequestMethod.POST)
    @ResponseBody
    public String PlaceOrder() {
        return null;
    }
    
    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    @ResponseBody
    public String getOrder() {
        return null;
    }

    @RequestMapping(value = "/user/wiki", method = RequestMethod.GET)
    @ResponseBody
    public String getWiki() {
        return null;
    }

    @RequestMapping(value = "/user/wiki", method = RequestMethod.POST)
    @ResponseBody
    public String setWiki() {
        return null;
    }

}
