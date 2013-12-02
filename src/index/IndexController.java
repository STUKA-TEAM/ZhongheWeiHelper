package index;

import java.io.ByteArrayInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tools.WeixinXMLTools;


@Controller
public class IndexController {
@RequestMapping(value = "/shiyan")
@ResponseBody
public String responseWeixin(@RequestParam(value = "signature") String signature,
		@RequestParam(value = "timestamp") String timestamp,
		@RequestParam(value = "nonce") String nonce,
		@RequestParam(value = "echostr") String echostr,
		@RequestBody String xml){
	SAXReader reader = new SAXReader();
	try {
		Document document = reader.read(new ByteArrayInputStream(xml.getBytes()));
		if(document.selectSingleNode("MsgType").getText().equals("event") && 
					document.selectSingleNode("Event").getText().equals("subscribe")){
			
			String dev = document.selectSingleNode("ToUserName").getText();
			String user = document.selectSingleNode("FromUserName").getText();
			
			return WeixinXMLTools.generateTextResponse(user, dev, "订阅成功！");
		}
		
		return "";
	} catch (DocumentException e) {
		return "";
	}
	
	
}
}
