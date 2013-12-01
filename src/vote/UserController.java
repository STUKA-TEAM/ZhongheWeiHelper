package vote;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.Constant;
import tools.GetSpecificImage;
import tools.ResponseMessage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @Title: UserController
 * @Description: 控制用户的可操作行为
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月21日
 */
@Controller
public class UserController {
	/**
	 * @Description: 获取正在发布的活动信息列表
	 * @return
	 */
	@RequestMapping(value = "/user/active/activitylist", method = RequestMethod.GET)
	public String getReleasingActivity(Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		List<VoteActivity> vList = vActivityDAO.getActivityListByStatus(Constant.ACTIVITY_RELEASE_STATUS);		
		model.addAttribute("activelist", vList);
		
		((ConfigurableApplicationContext)context).close();
		
		return "showActiveList";
	}
	
	/**
	 * Description: 获取用户指定活动的详细信息
	 * @param voteId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/show/activity", method = RequestMethod.GET)
	public String getOneActivity(@RequestParam(value = "voteId", required = true) int voteId,
			@CookieValue(value = "openId", required = false) String openId, Model model){
/*		if (openId == null || openId == "") {  //check openId
			return "exception";
		}*/
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		
		VoteActivity vActivity = vActivityDAO.getActivity(voteId);
		List<VoteItem> iList = vActivity.getViList();
		GetSpecificImage g = new GetSpecificImage ();   //reset image to the real path 
		if (vActivity.getVotePicture() != null) {
			vActivity.setVotePicture(g.getStandardImagePath(vActivity.getVotePicture()));
		}		
		for (int i = 0; i < iList.size(); i++) {
			VoteItem vItem = iList.get(i);
			if (vItem.getItemImage() != null) {
				vItem.setItemImage(g.getSmallImagePath(vItem.getItemImage()));
			}			
		}
		
		model.addAttribute("activity", vActivity);
		model.addAttribute("itemlist", iList);
		model.addAttribute("openId", openId);
		
		((ConfigurableApplicationContext)context).close();
		if (vActivity.getIsMultiChoice() == 0) {
			return "singleChoice";
		}
		else {
			return "multiChoice";
		}
	}
	
	/**
	 * @Description: 提交用户的投票选择，分单选/多选两种类型
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/user/submit/choice/single", method = RequestMethod.POST)
	@ResponseBody
	public String submitChoice(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		
		Gson gson = new Gson();
		Choice choice = gson.fromJson(json, Choice.class);
		int choiceId = choiceDAO.insertChoice(choice);
		
		ResponseMessage rMessage = new ResponseMessage();
		if (choiceId > 0) {
			rMessage.setStatus(true);
			rMessage.setMessage("Success");
		}
		else {
			rMessage.setStatus(false);
			rMessage.setMessage("Add failure!");
		}
		String rjson = gson.toJson(rMessage);
		((ConfigurableApplicationContext)context).close();
		
		return rjson;
	}
	
	@RequestMapping(value = "/user/submit/choice/multi", method = RequestMethod.POST)
	@ResponseBody
	public String submitChoices(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		
		Gson gson = new Gson();
		List<Choice> cList = gson.fromJson(json, new TypeToken<List<Choice>>(){}.getType());
		List<Integer> choiceIds = choiceDAO.insertChoiceList(cList);
		
		ResponseMessage rMessage = new ResponseMessage();	
		rMessage.setStatus(true);
		rMessage.setMessage("Success");
		for (int i = 0; i < choiceIds.size(); i++) {
			if (choiceIds.get(i) <= 0) {   //需要一些清除操作
				rMessage.setStatus(false);
				rMessage.setMessage("Add failure!");
				break;
			}
		}
		String rjson = gson.toJson(rMessage);
		((ConfigurableApplicationContext)context).close();
		
		return rjson;
	}
	
	/**
	 * @Description: 用户查看投票结果
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/user/activityresult", method = RequestMethod.GET)
	public String showUserResult(@RequestParam(value = "voteId", required = true) int voteId, 
			@CookieValue(value = "openId", required = false) String openId, Model model){
/*		if (openId == null || openId == "") {  //check openId
			return "exception";
		}*/
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteActivityDAO vActivityDAO = (VoteActivityDAO) context.getBean("VoteActivityDAO");
		VoteItemDAO vItemDAO = (VoteItemDAO) context.getBean("VoteItemDAO");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		
		VoteActivity vActivity = vActivityDAO.getActivity(voteId);
		List<ItemResult> iList = new ArrayList<ItemResult>();
		
		//query for itemResult
		List<VoteItem> vList = vItemDAO.getVoteItemList(voteId);
		GetSpecificImage g = new GetSpecificImage ();   //reset image to the real path 
		if (vActivity.getVotePicture() != null) {
			vActivity.setVotePicture(g.getStandardImagePath(vActivity.getVotePicture()));
		}	
		double sum = 0;  //计算总投票人数
		for (int i = 0; i < vList.size(); i++) {
			VoteItem vItem = vList.get(i);
			if (vItem.getItemImage() != null) {
				vItem.setItemImage(g.getSmallImagePath(vItem.getItemImage()));
			}	
			int count = choiceDAO.getChoiceNum(vItem.getItemId());
			sum = sum + count;
			ItemResult iResult = new ItemResult(vItem, count);
			iList.add(iResult);
		}
		for (int i = 0; i < iList.size(); i++) {  //计算百分比
			ItemResult iResult = iList.get(i);
			iResult.setPercent(iResult.getCount() / sum * 100);
		}
		
		model.addAttribute("activity", vActivity);
		model.addAttribute("openId", openId);
		model.addAttribute("activityresult", iList);
	     
	     ((ConfigurableApplicationContext)context).close();
	     
	    if (vActivity.getEnableAdvice() == 0) {
			return "showUserResultWithoutAdvice";
		}
	    else {
			return "showUserResult";
		}
	}
	
	/**
	 * @Description: 提交用户的评论
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/user/submit/advice", method = RequestMethod.POST)
	@ResponseBody
	public String submitAdvice(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		AdviceDAO adviceDAO = (AdviceDAO) context.getBean("AdviceDAO");
		
		Gson gson = new Gson();
		Advice advice = gson.fromJson(json, Advice.class);
		int adviceId = adviceDAO.insertAdvice(advice);
		
		ResponseMessage rMessage = new ResponseMessage();
		if (adviceId > 0) {
			rMessage.setStatus(true);
			rMessage.setMessage("Success");
		}else {
			rMessage.setStatus(false);
			rMessage.setMessage("Add failure!");
		}
		String rjson = gson.toJson(rMessage);
		((ConfigurableApplicationContext)context).close();
		 
		return rjson;
	}
}
