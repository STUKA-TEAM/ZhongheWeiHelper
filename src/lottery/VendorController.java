package lottery;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

import tools.Constant;

import com.google.gson.Gson;

/**
 * @Title: VendorController
 * @Description: 控制商家的可操作行为
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月21日
 */
@Controller
public class VendorController {
	/**
	 * @Description: 获取所有活动信息列表
	 * @return
	 */
	@RequestMapping(value = "/store/activitylist", method = RequestMethod.GET)
	public String getLotteryActivityList(Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		List<LotteryActivity> draftList = lActivityDAO.getActivityListByStatus(Constant.ACTIVITY_DRAFT_STATUS);		
		List<LotteryActivity> savedList = lActivityDAO.getActivityListByStatus(Constant.ACTIVITY_SAVE_STATUS);
		List<LotteryActivity> releasedList = lActivityDAO.getActivityListByStatus(Constant.ACTIVITY_RELEASE_STATUS);
		List<LotteryActivity> closedList = lActivityDAO.getActivityListByStatus(Constant.ACTIVITY_CLOSED_STATUS);
		
		model.addAttribute("draftlist", draftList);
		model.addAttribute("savedlist", savedList);
		model.addAttribute("releasedlist", releasedList);
		model.addAttribute("closedlist", closedList);
		
		((ConfigurableApplicationContext)context).close();
		
		return "showActivityList";
	}
	
	/**
	 * @Description: 创建新的活动
	 * @return
	 */
	@RequestMapping(value = "/store/newactivity/create", method = RequestMethod.GET)
	public String createActivity(){
		return "createActivity";
	}
	
	/**
	 * @Description: 再次利用已结束活动
	 * @param lotteryId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/store/newactivity/reuse", method = RequestMethod.GET)
	public String reuseActivity(@RequestParam(value = "lotteryId", required = true) int lotteryId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		LotteryActivity lActivity = lActivityDAO.getActivity(lotteryId);
		model.addAttribute("activity", lActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return "reuseActivity";
	}
	
	/**
	 * @Description: 3种操作 草稿+保存+发布  后两种需要后台验证
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/store/draft/activity/create", method = RequestMethod.POST)
	@ResponseBody
	public String draftNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		Gson gson = new Gson();
		LotteryActivity lActivity = gson.fromJson(json, LotteryActivity.class);
		
		lActivity.setLotteryStatus(Constant.ACTIVITY_DRAFT_STATUS);
		int lotteryId = lActivityDAO.insertActivity(lActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return lotteryId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/draft/activity/update", method = RequestMethod.POST)
	@ResponseBody
	public String draftExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		Gson gson = new Gson();
		LotteryActivity lActivity = gson.fromJson(json, LotteryActivity.class);
		
		lActivity.setLotteryStatus(Constant.ACTIVITY_DRAFT_STATUS);
		int result = lActivityDAO.updateActivity(lActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/save/activity/create", method = RequestMethod.POST)
	@ResponseBody
	public String saveNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		Gson gson = new Gson();
		LotteryActivity lActivity = gson.fromJson(json, LotteryActivity.class);
		
		lActivity.setLotteryStatus(Constant.ACTIVITY_SAVE_STATUS);
		int lotteryId = lActivityDAO.insertActivity(lActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return lotteryId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/save/activity/update", method = RequestMethod.POST)
	@ResponseBody
	public String saveExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
        LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		Gson gson = new Gson();
		LotteryActivity lActivity = gson.fromJson(json, LotteryActivity.class);
		
		lActivity.setLotteryStatus(Constant.ACTIVITY_SAVE_STATUS);
		int result = lActivityDAO.updateActivity(lActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/release/activity/create", method = RequestMethod.POST)
	@ResponseBody
	public String releaseNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
        LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		Gson gson = new Gson();
		LotteryActivity lActivity = gson.fromJson(json, LotteryActivity.class);
		
		lActivity.setLotteryStatus(Constant.ACTIVITY_RELEASE_STATUS);
		int lotteryId = lActivityDAO.insertActivity(lActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return lotteryId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/release/activity/update", method = RequestMethod.POST)
	@ResponseBody
	public String releaseExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
        LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		Gson gson = new Gson();
		LotteryActivity lActivity = gson.fromJson(json, LotteryActivity.class);
		
		lActivity.setLotteryStatus(Constant.ACTIVITY_RELEASE_STATUS);
		int result = lActivityDAO.updateActivity(lActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 删除活动
	 * @param lotteryId
	 * @return
	 */
	@RequestMapping(value = "/store/delete/activity",  method = RequestMethod.POST)
	@ResponseBody
	public String deleteActivity(@RequestBody int lotteryId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		int result = lActivityDAO.deleteByLotteryId(lotteryId);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 关闭活动
	 * @param lotteryId
	 * @return
	 */
	@RequestMapping(value = "/store/close/activity", method = RequestMethod.POST)
	@ResponseBody
	public String closeActivity(@RequestBody int lotteryId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		int lotteryStatus = lActivityDAO.getLotteryStatus(lotteryId);
		int result = 0;
		
		if (lotteryStatus == Constant.ACTIVITY_RELEASE_STATUS) {
			Timestamp current = new Timestamp(System.currentTimeMillis());
		    result = lActivityDAO.updateEndDateAndStatus(lotteryId, current, Constant.ACTIVITY_CLOSED_STATUS);
		}
				
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 发布活动
	 * @param lotteryId
	 * @return
	 */
	@RequestMapping(value = "/store/release/activity/immediate", method = RequestMethod.POST)
	@ResponseBody
	public String releaseActivity(@RequestBody int lotteryId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		
		int lotteryStatus = lActivityDAO.getLotteryStatus(lotteryId);
		int result = 0;
		
		if (lotteryStatus == Constant.ACTIVITY_SAVE_STATUS) {
			Timestamp current = new Timestamp(System.currentTimeMillis());
		    result = lActivityDAO.updateStartDateAndStatus(lotteryId, current, Constant.ACTIVITY_RELEASE_STATUS);
		}
				
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 商家查看抽奖结果
	 * @param lotteryId
	 * @return
	 */
	@RequestMapping(value = "/store/activityresult", method = RequestMethod.GET)
	public String showVendorResult(@RequestParam(value = "lotteryId", required = true) int lotteryId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LotteryActivityDAO lActivityDAO = (LotteryActivityDAO) context.getBean("LotteryActivityDAO");
		LuckyRecordDAO luckyRecordDAO = (LuckyRecordDAO) context.getBean("LuckyRecordDAO");
		
	    LotteryActivity lActivity = lActivityDAO.getActivity(lotteryId);
	    List<LotteryResult> rList = new ArrayList<LotteryResult>();
		
		//query for lotteryResult
	    List<LotteryPrize> pList = lActivity.getLpList();
		for(int i = 0; i < pList.size(); i++){
			int actualNum = luckyRecordDAO.getActualNum(pList.get(i).getPrizeId());
			LotteryResult lResult = new LotteryResult();
			lResult.setLotteryPrize(pList.get(i));
			lResult.setActualNum(actualNum);
			rList.add(lResult);
		}	     
		
		model.addAttribute("activityinfo", lActivity);
		model.addAttribute("resultlist", rList);
		
	     ((ConfigurableApplicationContext)context).close();
	     
	     return "showStoreResult";
	}
	
	/**
	 * @Description: 请求兑奖界面
	 * @return
	 */
	@RequestMapping(value = "/store/claim/showprize", method = RequestMethod.GET)
	public String claimPrize(){
		return "claimPrize";
	}
	
	/**
	 * @Description: 根据兑奖码查询数据库记录
	 * @param prizeKey
	 * @return
	 */
	@RequestMapping(value = "/store/search/luckyrecord", method = RequestMethod.GET)
	@ResponseBody
	public String searchLuckyRecord(@RequestParam(value = "prizeKey", required = true)String prizeKey){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LuckyRecordDAO luckyRecordDAO = (LuckyRecordDAO) context.getBean("LuckyRecordDAO");
		
		int count = luckyRecordDAO.getNumByPrizeKey(prizeKey);
		int result = 0;
		
		if(count == 1){
			LuckyRecord lRecord = luckyRecordDAO.getRecordByPrizeKey(prizeKey);			
			Gson gson = new Gson();
			String json = gson.toJson(lRecord);
			result = 1;
		}
		
		((ConfigurableApplicationContext)context).close();
		
		return result == 0 ? "Failed" : "Success";
	}
	
	/**
	 * @Description: 根据中奖Id进行兑奖操作
	 * @param luckyId
	 * @return
	 */
	@RequestMapping(value = "/store/claim/getprize", method = RequestMethod.POST)
	@ResponseBody
	public String getPrize(@RequestBody int luckyId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		LuckyRecordDAO luckyRecordDAO = (LuckyRecordDAO) context.getBean("LuckyRecordDAO");
		
		int result = luckyRecordDAO.updateStatusByLuckyId(luckyId, Constant.PRIZE_OFF);
		
		((ConfigurableApplicationContext)context).close();
		
		return result == 0 ? "Failed" : "Success";
	}
}
