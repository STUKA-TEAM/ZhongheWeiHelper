package tools;

import java.io.IOException;


import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.omg.CORBA.PUBLIC_MEMBER;

public class WeixinXMLTools {
	/**
	 * 
	 * @param openId  要回复的用户的openId
	 * @param devName 我方公众账号名
	 * @param content
	 * @return
	 * @throws IOException
	 */
public static String  generateTextResponse(String openId, String devName,
		String content){
	try{
		Document newDocument = DocumentHelper.createDocument();
		Element element = newDocument.addElement("xml");
		element.addElement("ToUserName").setText(openId);
		element.addElement("FromUserName").setText(devName);
		element.addElement("CreateTime").setText(Long.toString(System.currentTimeMillis()));
		element.addElement("MsgType").setText("text");
		element.addElement("Content").setText(content);
		StringWriter stringWriter = new StringWriter(); 
		XMLWriter xmlWriter = new XMLWriter(stringWriter);
		xmlWriter.write(newDocument);
	return stringWriter.toString();
	}catch(Exception exception){
		return "";
	}
	
}

public static String generatePicTextResponse(String openId, String devName,
		WeiXinPicTextItem item){
	try{
		Document newDocument = DocumentHelper.createDocument();
		Element element = newDocument.addElement("xml");
		element.addElement("ToUserName").setText(openId);
		element.addElement("FromUserName").setText(devName);
		element.addElement("CreateTime").setText(Long.toString(System.currentTimeMillis()));
		element.addElement("MsgType").setText("news");
		element.addElement("ArticleCount").setText("1");
		Element articles = element.addElement("Articles");
		Element articleItem = articles.addElement("item");
		articleItem.addElement("Title").setText(item.getTitle());;
		articleItem.addElement("Description").setText(item.getDescription());;
		articleItem.addElement("PicUrl").setText(item.getPicUrl());;
		articleItem.addElement("Url").setText(item.getUrl());;
		StringWriter stringWriter = new StringWriter(); 
		XMLWriter xmlWriter = new XMLWriter(stringWriter);
		xmlWriter.write(newDocument);
	return stringWriter.toString();
	}catch(Exception exception){
		return "";
	}
}


public static void main(String[] strings) throws IOException{
	generateTextResponse("openId","zhonghe","hello");
}
}
