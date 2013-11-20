package order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String PostDish() {
        return null;
    }

    @RequestMapping(value = "/store/dish/del", method = RequestMethod.DELETE)
    @ResponseBody
    public String delDish() {
        return null;
    }

    @RequestMapping(value = "/store/dishs", method = RequestMethod.GET)
    @ResponseBody
    public String getDish() {
        return null;
    }

    @RequestMapping(value = "/store/menu", method = RequestMethod.POST)
    @ResponseBody
    public String PostMenu() {
        return null;
    }

    @RequestMapping(value = "/store/menu", method = RequestMethod.GET)
    @ResponseBody
    public String GetMenu() {
        return null;
    }

    @RequestMapping(value = "/store/order", method = RequestMethod.POST)
    @ResponseBody
    public String PostOrder() {
        return null;
    }

    @RequestMapping(value = "/store/order", method = RequestMethod.GET)
    @ResponseBody
    public String GetOrder() {
        return null;
    }

    @RequestMapping(value = "/store/wiki", method = RequestMethod.POST)
    @ResponseBody
    public String PostWiki() {
        return null;
    }

    @RequestMapping(value = "/store/wiki", method = RequestMethod.GET)
    @ResponseBody
    public String GetWiki() {
        return null;
    }
}
