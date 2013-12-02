package lottery;

import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import tools.Constant;
import tools.RandomUtil;

/**
 * @Title: UserController
 * @Description: 控制用户的可操作行为
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月25日
 */
@Controller
public class UserController {
	/**
	 * @Description: 获取正在发布的活动信息列表;
	 * 若当前正在发布的活动数量为1，则直接显示该活动详情
	 * @return
	 */
	@RequestMapping(value = "/user/active/activitylist", method = RequestMethod.GET)
	public String getReleasingActivity(@RequestParam(value = "openId", required = true)String openId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		LotteryRecordDAO lotteryRecordDAO = (LotteryRecordDAO) context.getBean("LotteryRecordDAO");
		
		List<LotteryActivity> aList = lActivityDAO.getActivityListByStatus(Constant.ACTIVITY_RELEASE_STATUS);
		if (aList.size() == 1) {
			LotteryActivity lActivity = aList.get(0);
			int lotteryId = lActivity.getLotteryId();
			int chanceLeft = lActivityDAO.getChanceNum(lotteryId) - lotteryRecordDAO.getChanceUsed(openId, lotteryId);
			
			model.addAttribute("activityinfo", lActivity);
			model.addAttribute("chanceleft", chanceLeft);
			((ConfigurableApplicationContext)context).close();
			
			return "showLotteryActivity";
		}
		
		model.addAttribute("activelist", aList);
		((ConfigurableApplicationContext)context).close();
		
		return "showActiveList";
	}
	
	/**
	 * @Description: 返回活动详情界面
	 * @param openId
	 * @param lotteryId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/show/activity", method = RequestMethod.GET)
	public String showLotteryActivity(@RequestParam(value = "openId", required = true)
	    String openId, @RequestParam(value = "lotteryId", required = true)int lotteryId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		LotteryRecordDAO lotteryRecordDAO = (LotteryRecordDAO) context.getBean("LotteryRecordDAO");
		
		LotteryActivity lActivity = lActivityDAO.getActivity(lotteryId);
		int chanceLeft = lActivityDAO.getChanceNum(lotteryId) - lotteryRecordDAO.getChanceUsed(openId, lotteryId);
		
		model.addAttribute("activityinfo", lActivity);
		model.addAttribute("chanceleft", chanceLeft);
		((ConfigurableApplicationContext)context).close();
		
		return "showLotteryActivity";
	}
	
	/**
	 * @Description: 获取用户是否中奖信息
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/user/lottery", method = RequestMethod.POST)
	@ResponseBody
	public String doLottery(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		LotteryPrizeDAO lPrizeDAO = (LotteryPrizeDAO) context.getBean("LotteryPrizeDAO");
		LotteryRecordDAO lotteryRecordDAO = (LotteryRecordDAO) context.getBean("LotteryRecordDAO");
		LuckyRecordDAO luckyRecordDAO = (LuckyRecordDAO) context.getBean("LuckyRecordDAO");
		
		Gson gson = new Gson();
		LotteryIdentifier identifier = gson.fromJson(json, LotteryIdentifier.class);
		int lotteryId = identifier.getLotteryId();
		String openId = identifier.getOpenId();
		
		int chanceLeft = lActivityDAO.getChanceNum(lotteryId) - lotteryRecordDAO.getChanceUsed(openId, lotteryId);
		if(chanceLeft >= 1){ //可以抽奖
			List<LotteryPrize> pList = lPrizeDAO.getLotteryPrizeListWithOrder(lotteryId);			
			/**抽奖过程*/
			LuckyResult luckyResult = null;
			Random rand = new Random();
			double lotteryResult = rand.nextDouble();   
			double sumPercent = 0;
			
			for(int i = 0; i < pList.size(); i ++){
				LotteryPrize temp = pList.get(i);
				if((lotteryResult >= sumPercent) && (lotteryResult < sumPercent + temp.getLuckyPercent().doubleValue())){
					synchronized (this) {
					int restLuckyNum = temp.getLuckyNum() - luckyRecordDAO.getActualNum(temp.getPrizeId());										
					if(restLuckyNum > 0){ // 中奖						
						LotteryRecord lotteryRecord = new LotteryRecord(lotteryId, openId, Constant.WITH_PRIZE);
						lotteryRecordDAO.insertLotteryRecord(lotteryRecord);
						
						//兑奖码依赖于prizeId
						LuckyRecord luckyRecord = new LuckyRecord(temp.getPrizeId(), openId, null, Constant.PRIZE_ON);
						int luckyId = luckyRecordDAO.insertLuckyRecord(luckyRecord);
						String prizeKey = RandomUtil.generateMixedString(luckyId, Constant.PRIZEKEY_LENGTH);
						luckyRecordDAO.updatePrizeKeyByLuckyId(luckyId, prizeKey);
						
						luckyResult = new LuckyResult(temp.getPrizeName(), temp.getPrizeContent(), prizeKey, chanceLeft - 1);
					}
					else { // 奖品被领完
						System.out.println("no more prizes, poor guy!");
					}
					}
					break;
				}
				sumPercent += temp.getLuckyPercent().doubleValue();
			}
			if (luckyResult == null) {    // 没有中奖    
				LotteryRecord lotteryRecord = new LotteryRecord(lotteryId, openId, Constant.WITHOUT_PRIZE);
				lotteryRecordDAO.insertLotteryRecord(lotteryRecord);
				luckyResult = new LuckyResult();
				luckyResult.setChanceLeft(chanceLeft - 1);
			}
		}
		
		((ConfigurableApplicationContext)context).close();
		
		return "";
	}
}
