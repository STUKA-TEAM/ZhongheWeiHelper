package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: CommonValidationTools
 * @Description: all the tools to validate input
 * @Company: ZhongHe
 * @author BelieveIt
 * @date 2013年11月21日
 */
public class CommonValidationTools {
	
	/**
	 * 
	 * @param email
	 * @return 是否符合邮件规范
	 */
	public boolean checkEmail(String email){
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
		Pattern regex = Pattern.compile(check);  
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param phone
	 * @return 是否符合中国手机号规则
	 */
	public boolean checkPhone(String phone){
		String check = "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(phone);		 
		return matcher.matches();
	}
	
	/**
	 * 
	 * @param multipartFile
	 * @return 是否超过2MB
	 */
	public boolean checkImageSize(MultipartFile multipartFile) {
		//不能超过 2MB
		if(multipartFile.getSize() > 2100000){
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 
	 * @param multipartFile
	 * @return 是否是图片格式
	 */
	public boolean checkImageType(MultipartFile multipartFile) {
		if (multipartFile.getContentType() != "image/jpeg" 
				&& multipartFile.getContentType() != "image/png") {
			return false;
		}else {
			return true;
		}
	}
}
