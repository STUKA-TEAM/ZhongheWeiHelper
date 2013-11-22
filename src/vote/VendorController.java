package vote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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
	@RequestMapping(value = "/activitylist", method = RequestMethod.GET)
	@ResponseBody
	public String getVoteActivityList(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		List<VoteActivity> vList = vActivityDAO.getActivityList();
		
		Gson gson = new Gson();
		String json = gson.toJson(vList); 
		
		((ConfigurableApplicationContext)context).close();
		
		return json;
	}
	
	/**
	 * @Description: 0-新建 1-更新 
	 * 3种操作 草稿+保存+发布
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/draft0", method = RequestMethod.POST)
	public String draftNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Gson gson = new Gson();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_DRAFT_STATUS);
		int voteId = vActivityDAO.insertActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return voteId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/draft1", method = RequestMethod.POST)
	public String draftExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Gson gson = new Gson();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_DRAFT_STATUS);
		int result = vActivityDAO.updateActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/save0", method = RequestMethod.POST)
	public String saveNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Gson gson = new Gson();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_SAVE_STATUS);
		int voteId = vActivityDAO.insertActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return voteId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/save1", method = RequestMethod.POST)
	public String saveExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Gson gson = new Gson();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_SAVE_STATUS);
		int result = vActivityDAO.updateActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/release0", method = RequestMethod.POST)
	public String releaseNewActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Gson gson = new Gson();
		VoteActivity vActivity = gson.fromJson(json, VoteActivity.class);
		
		vActivity.setVoteStatus(Constant.ACTIVITY_RELEASE_STATUS);
		int voteId = vActivityDAO.insertActivity(vActivity);
		
		((ConfigurableApplicationContext)context).close();
		
		return voteId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/release1", method = RequestMethod.POST)
	public String releaseExistActivity(@RequestBody String json) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Gson gson = new Gson();
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
	@RequestMapping(value = "/delete",  method = RequestMethod.POST)
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
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public String closeActivity(@RequestBody int voteId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Timestamp current = new Timestamp(System.currentTimeMillis());
		int result = vActivityDAO.updateEndDateAndStatus(voteId, current, Constant.ACTIVITY_CLOSED_STATUS);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 发布活动
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/release", method = RequestMethod.POST)
	public String releaseActivity(@RequestBody int voteId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		Timestamp current = new Timestamp(System.currentTimeMillis());
		int result = vActivityDAO.updateStartDateAndStatus(voteId, current, Constant.ACTIVITY_RELEASE_STATUS);
		
		((ConfigurableApplicationContext)context).close();
		
		return result > 0 ? "Success" : "Failed";
	}
	
	/**
	 * @Description: 商家查看投票结果
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/result0", method = RequestMethod.GET)
	@ResponseBody
	public String showVendorResult(@RequestParam(value = "voteId", required = true) int voteId){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		VoteItemDAO vItemDAO = (VoteItemDAO) context.getBean("VoteItemDAO");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		AdviceDAO adviceDAO = (AdviceDAO) context.getBean("AdviceDAO");
		
		VoteResult vResult = new VoteResult();
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
		vResult.setItemResult(iList);
		vResult.setAdviceList(aList);
		
		 Gson gson = new Gson();
	     String json = gson.toJson(vResult);
	     
	     ((ConfigurableApplicationContext)context).close();
	     
	     return json;
	}
	
}
