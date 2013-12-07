package vote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lottery.LotteryActivity;
import lottery.LotteryActivityDAO;

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
import com.google.gson.GsonBuilder;

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
	public String getVoteActivityList(Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		List<VoteActivity> draftList = vActivityDAO.getActivityListByStatus(Constant.ACTIVITY_DRAFT_STATUS);		
		List<VoteActivity> savedList = vActivityDAO.getActivityListByStatus(Constant.ACTIVITY_SAVE_STATUS);
		List<VoteActivity> releasedList = vActivityDAO.getActivityListByStatus(Constant.ACTIVITY_RELEASE_STATUS);
		List<VoteActivity> closedList = vActivityDAO.getActivityListByStatus(Constant.ACTIVITY_CLOSED_STATUS);
		
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
	public String createNewActivity(){
		return "createActivity";
	}
	
	/**
	 * @Description: 编辑选中的活动  --> update
	 * @param voteId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/store/newactivity/edit", method = RequestMethod.GET)
	public String editActivity(@RequestParam(value = "voteId", required = true) int voteId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		VoteActivity vActivity = vActivityDAO.getActivity(voteId);

		model.addAttribute("activity", vActivity);
		model.addAttribute("votelist", vActivity.getViList());
		
		((ConfigurableApplicationContext)context).close();
		
		return "editActivity";
	}
	
	/**
	 * @Description: 再次利用已结束活动  --> insert
	 * @param voteId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/store/newactivity/reuse", method = RequestMethod.GET)
	public String reuseActivity(@RequestParam(value = "voteId", required = true) int voteId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		VoteActivity vActivity = vActivityDAO.getActivity(voteId);
		model.addAttribute("activity", vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return "createActivity";
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
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-mm-dd'T'hh:mm");
		Gson gson = builder.create();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_DRAFT_STATUS);
		int voteId = vActivityDAO.insertActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return voteId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/draft/activity/update", method = RequestMethod.POST)
	@ResponseBody
	public String draftExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-mm-dd'T'hh:mm");
		Gson gson = builder.create();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_DRAFT_STATUS);
		int result = vActivityDAO.updateActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/save/activity/create", method = RequestMethod.POST)
	@ResponseBody
	public String saveNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-mm-dd'T'hh:mm");
		Gson gson = builder.create();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_SAVE_STATUS);
		int voteId = vActivityDAO.insertActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return voteId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/save/activity/update", method = RequestMethod.POST)
	@ResponseBody
	public String saveExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-mm-dd'T'hh:mm");
		Gson gson = builder.create();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_SAVE_STATUS);
		int result = vActivityDAO.updateActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/release/activity/create", method = RequestMethod.POST)
	@ResponseBody
	public String releaseNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-mm-dd'T'hh:mm");
		Gson gson = builder.create();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_RELEASE_STATUS);
		int voteId = vActivityDAO.insertActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return voteId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/store/release/activity/update", method = RequestMethod.POST)
	@ResponseBody
	public String releaseExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-mm-dd'T'hh:mm");
		Gson gson = builder.create();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_RELEASE_STATUS);
		int result = vActivityDAO.updateActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 删除活动
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/store/delete/activity",  method = RequestMethod.POST)
	@ResponseBody
	public String deleteActivity(@RequestBody int voteId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		int result = vActivityDAO.deleteByVoteId(voteId);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 关闭活动
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/store/close/activity", method = RequestMethod.POST)
	@ResponseBody
	public String closeActivity(@RequestBody int voteId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		int voteStatus = vActivityDAO.checkVoteStatus(voteId);
		int result = 0;
		
		if (voteStatus == Constant.ACTIVITY_RELEASE_STATUS) {
			Timestamp current = new Timestamp(System.currentTimeMillis());
		    result = vActivityDAO.updateEndDateAndStatus(voteId, current, Constant.ACTIVITY_CLOSED_STATUS);
		}
				
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 发布活动
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/store/release/activity/immediate", method = RequestMethod.POST)
	@ResponseBody
	public String releaseActivity(@RequestBody int voteId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		int voteStatus = vActivityDAO.checkVoteStatus(voteId);
		int result = 0;
		
		if (voteStatus == Constant.ACTIVITY_SAVE_STATUS) {
			Timestamp current = new Timestamp(System.currentTimeMillis());
		    result = vActivityDAO.updateStartDateAndStatus(voteId, current, Constant.ACTIVITY_RELEASE_STATUS);
		}
				
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 商家查看投票结果
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/store/activityresult", method = RequestMethod.GET)
	public String showVendorResult(@RequestParam(value = "voteId", required = true) int voteId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		VoteItemDAO vItemDAO = (VoteItemDAO) context.getBean("VoteItemDAO");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		AdviceDAO adviceDAO = (AdviceDAO) context.getBean("AdviceDAO");
		
	//	VoteResult vResult = new VoteResult();
		List<ItemResult> iList = new ArrayList<ItemResult>();
		List<Advice> aList = new ArrayList<Advice>();
		
		//query for itemResult
		List<VoteItem> vList = vItemDAO.getVoteItemList(voteId);
		for (int i = 0; i < vList.size(); i++) {
			int count = choiceDAO.getChoiceNum(vList.get(i).getItemId());
			ItemResult iResult = new ItemResult(vList.get(i), count);
			iList.add(iResult);
		}
		
		//query for advice
		if (vActivityDAO.checkEnableAdvice(voteId) == 1) {
			aList = adviceDAO.getAdviceList(voteId);
		}
		
		//finally build the model
	  /*vResult.setItemResult(iList);
		vResult.setAdviceList(aList);*/
		
	//	model.addAttribute("activityresult", vResult);
		model.addAttribute("resultlist", iList);
		model.addAttribute("advicelist", aList);
		
	     
	     ((ConfigurableApplicationContext)context).close();
	     
	     return "showStoreResult";
	}
	
}
