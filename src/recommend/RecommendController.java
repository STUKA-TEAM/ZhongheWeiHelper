package recommend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title: RecommendController
 * @Description:
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月20日
 */
@Controller
public class RecommendController {
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @ResponseBody
    public String getRecommendList(
            Model model,
            @RequestParam(value = "userLocation", required = false) String userLocation,
            @RequestParam(value = "storeName", required = false) String storeName) {
        Recommend recommend = new Recommend(userLocation, storeName);
        return recommend.getStoreList();
    }
}
