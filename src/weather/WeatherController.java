package weather;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.Constant;
import tools.HttpUtil;

import com.google.gson.Gson;

@Controller
public class WeatherController {
	@RequestMapping(value="/user/initialView")
	String initial(@RequestParam(value = "openId")String openId, Model model){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		WeatherCityDAO weatherCityDao = (WeatherCityDAO)context.getBean("WeatherCityDAO");
		List<SavedCity> cities = weatherCityDao.getSavedCityListByOpenId(openId);
		model.addAttribute("savedCities", cities);
		return "searchWeather";
	}
	
	@RequestMapping(value = "/user/savecity", method = RequestMethod.POST)
	@ResponseBody
	String saveCity(@RequestBody String json){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("All-Modules.xml");
		WeatherCityDAO weatherCityDao = (WeatherCityDAO)context.getBean("WeatherCityDAO");
		Gson gson = new Gson();
		SavedCity savedCity = (SavedCity)gson.fromJson(json, SavedCity.class);
		int insertId = weatherCityDao.insertCity(savedCity.getOpenId(), savedCity.getCity());	
		if (insertId == 0) {
			return "n";
		} else {
			return "y";
		}
	}
	
	@RequestMapping(value = "/user/searchcity", method = RequestMethod.POST)
	String searchWeather(@RequestBody String city, Model model){
		try {
			String parameterString = "?location="+city+"&output=json&ak="+generateBaiduKey();
			//status "success":正确 ; "No result available":输入城市有误  ; 5:key出错
			String jsonFromBaidu = HttpUtil.doGet(Constant.WEATHER_URL, 
					parameterString, "utf-8", false);
			Gson gson = new Gson();
			WeatherInfoContainer weatherInfo = gson.fromJson(jsonFromBaidu, 
					WeatherInfoContainer.class);
			String status = weatherInfo.getStatus();
			String baiduSatus = "keyerror";
			
			if(status.equals("success")){
				baiduSatus = "success";
				ArrayList<WeatherData> weatherDatas = 
						weatherInfo.getResults().get(0).getWeather_data();
				model.addAttribute("weatherInfo", weatherDatas);
			}else if (status.equals("No result available")) {
				baiduSatus = "noresult";
			}
			
			model.addAttribute("status", baiduSatus);		
			return "searchResult";
		} catch (Exception e) {
			model.addAttribute("status", "keyerror");		
			return "searchResult";
		}
		
	}
	
	private String generateBaiduKey(){
		String[] keys = {
			"NyRDkOwbgivbtqvgtRjoZTaM",	
			"rqtAIB1PMp4iSTigg1AVGj1k",
			"FAFtGnFkrqVKF4Hc2XHRqtV7",
			"z13ISgUAini55qUKnwVqVgZk",
			"nlo4G4zckxnsrbfVqwB1ehcb",
			"HsUb7O3d9pHktvtg9m7eLtGO",
			"WAnGLW4ZAAgTToO98ydssjEH",
			"O58V1QxIwLGRVY3oY8WvICMB",
			"kDV6jFbsp4yGQ7jKRy0R7GQz",
			"171E5DB338edb1296900e6a3e4bc07de",
			"FE7671d2e383649c5e7747d56c385eca",
			"2GIVV6IOTjvqUyOvhdpWtU4K",
			"hoD6uUIHL0O3w3Bnhm48KAqM",
			"motEhAfjH2Hge1bG1t9TZq5x",
			"Yha64kiYfGeWEW6o2WBfUbPq",
			"Ks52MYv6iIT60ArddVM654tK",
			"ko6GWUWqoC3T6diZjq4Ahr1i",
			"lsG6ogYOVgSbKGfgjsalG64I",
			"PWFniUmG9SMyIVlp7Nm24MRC"
		};
		Double random = Math.random()*19;
		int randomInt = (int)Math.ceil(random);
		return keys[randomInt-1];	
	}
}
