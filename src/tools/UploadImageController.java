package tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;


@Controller
public class UploadImageController {
	@Autowired
	@Qualifier("config")
	private Properties properties;	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	 public String handleFormUpload(
        @RequestParam("file") MultipartFile fileFromForm) {
		UploadImageResponseMessage responseMessage = new UploadImageResponseMessage();
		  Gson gson = new Gson();
		  if (!fileFromForm.isEmpty()) {
              try {
				InputStream inputStream = fileFromForm.getInputStream();		
				CommonValidationTools commonValidationTools = new CommonValidationTools();
	
				if (!commonValidationTools.checkImageSize(fileFromForm)) {
					responseMessage.setStatus(false);
					responseMessage.setMessage("文件大小超过限制！");
					return gson.toJson(responseMessage);
				}else {
					ImageUtil imageUtil = new ImageUtil();
					String relativePathID = imageUtil.saveMutiSize(inputStream);
					responseMessage.setStatus(true);
					responseMessage.setMessage("上传成功！");
					responseMessage.setLink(relativePathID);					
					return gson.toJson(responseMessage);
				}
				
			} catch (IOException e) {
				responseMessage.setStatus(false);
				responseMessage.setMessage("上传失败！");
				return gson.toJson(responseMessage);
			}                  
		  }else {
              responseMessage.setStatus(false);
			  responseMessage.setMessage("请选择文件！");
			  return gson.toJson(responseMessage); 
		}
    }
	
}
