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
		ResponseMessage responseMessage = new ResponseMessage();
		  Gson gson = new Gson();
		  if (!fileFromForm.isEmpty()) {
              try {
				InputStream inputStream = fileFromForm.getInputStream();
				String fileName = fileFromForm.getOriginalFilename();			
				CommonValidationTools commonValidationTools = new CommonValidationTools();
	
				if (!commonValidationTools.checkImageSize(fileFromForm)) {
					responseMessage.setStatus(false);
					responseMessage.setMessage("文件大小超过限制！");
					return gson.toJson(responseMessage);
					
				} else if (!commonValidationTools.checkImageType(fileFromForm)) {
					responseMessage.setStatus(false);
					responseMessage.setMessage("请确认上传的文件为jpg或者png格式！");
					return gson.toJson(responseMessage);
				}else {
					ImageUtil imageUtil = new ImageUtil();
					String relativePath = imageUtil.scaleFill(inputStream, 
							50, 50, fileFromForm.getContentType());
					
					responseMessage.setStatus(true);
					responseMessage.setMessage("上传成功！");
					responseMessage.setDataString(relativePath);
					
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
