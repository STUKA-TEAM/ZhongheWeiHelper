package message;

import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import weather.WeatherCityDAO;

@Controller
public class MessageController {
@RequestMapping(value="/initial",method=RequestMethod.GET)
public String initialView(Model model){
	return "addMessage";
}

@RequestMapping(value="/addmessage",method=RequestMethod.POST)
@ResponseBody
public String addMessage(@RequestBody String json){
	try{
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		MessageDAO messageDAO = (MessageDAO)context.getBean("MessageDAO");
		Gson gson = new Gson();
		Message message = gson.fromJson(json, Message.class);
		Timestamp current = new Timestamp(System.currentTimeMillis());
		message.setDate(current);
		messageDAO.insertMessage(message);
		return "y";
	}catch(Exception e){
		return "n";
	}
}
}
