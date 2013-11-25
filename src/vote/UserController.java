package vote;

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
	 * @Description: 提交用户的投票选择，分单选/多选两种类型
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/user/submit/choice", method = RequestMethod.POST)
	public String submitChoice(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		
		Gson gson = new Gson();
		Choice choice = gson.fromJson(json, Choice.class);
		int choiceId = choiceDAO.insertChoice(choice);
		
		((ConfigurableApplicationContext)context).close();
		
		return choiceId > 0 ? "Success" : "Failed";
	}
	
	@RequestMapping(value = "/user/submit/choices", method = RequestMethod.POST)
	public String submitChoices(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		
		Gson gson = new Gson();
		List<Choice> cList = gson.fromJson(json, new TypeToken<List<Choice>>(){}.getType());
		List<Integer> choiceIds = choiceDAO.insertChoiceList(cList);
		
		((ConfigurableApplicationContext)context).close();
		
		for (int i = 0; i < choiceIds.size(); i++) {
			if (choiceIds.get(i) <= 0) {
				return "Failed";
			}
		}
		return "Success";
	}
	
	/**
	 * @Description: 用户查看投票结果
	 * @param voteId
	 * @return
	 */
	@RequestMapping(value = "/user/activityresult", method = RequestMethod.GET)
	@ResponseBody
	public String showUserResult(@RequestParam(value = "voteId", required = true) int voteId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		VoteItemDAO vItemDAO = (VoteItemDAO) context.getBean("VoteItemDAO");
		ChoiceDAO choiceDAO = (ChoiceDAO) context.getBean("ChoiceDAO");
		
		List<ItemResult> iList = new ArrayList<ItemResult>();
		
		//query for itemResult
		List<VoteItem> vList = vItemDAO.getVoteItemList(voteId);
		for (int i = 0; i < vList.size(); i++) {
			int count = choiceDAO.getChoiceNum(vList.get(i).getItemId());
			ItemResult iResult = new ItemResult(vList.get(i), count);
			iList.add(iResult);
		}
		
		model.addAttribute("activityresult", iList);
	     
	     ((ConfigurableApplicationContext)context).close();
	     
	     return "showUserResult";
	}
	
	/**
	 * @Description: 提交用户的评论
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/user/submit/advice", method = RequestMethod.POST)
	public String submitAdvice(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		AdviceDAO adviceDAO = (AdviceDAO) context.getBean("AdviceDAO");
		
		Gson gson = new Gson();
		Advice advice = gson.fromJson(json, Advice.class);
		int adviceId = adviceDAO.insertAdvice(advice);
		
		((ConfigurableApplicationContext)context).close();
		 
		return adviceId > 0 ? "Success" : "Failed";
	}
}
