package index;


import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.WeixinXMLTools;


@Controller
public class IndexController {
@RequestMapping(value = "/shiyan")
@ResponseBody
public String responseWeixin(@RequestParam(value = "signature", required=false) String signature,
		@RequestParam(value = "timestamp",required=false) String timestamp,
		@RequestParam(value = "nonce",required=false) String nonce,
		@RequestParam(value = "echostr",required=false) String echostr,
		HttpServletRequest request
	){
	SAXReader reader = new SAXReader();
	try {
		Document document = reader.read(request.getInputStream());
		if(document.selectSingleNode("MsgType").getText().equals("event") && 
					document.selectSingleNode("Event").getText().equals("subscribe")){
			
			String dev = document.selectSingleNode("ToUserName").getText();
			String user = document.selectSingleNode("FromUserName").getText();
			
			return WeixinXMLTools.generateTextResponse(user, dev, "订阅成功！");
		}
		
		return "";
	} catch (Exception e) {
		return "";
	}
	
	
}
}
