package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SaveUploadedFile {
	/**
	 * 
	 * @param path
	 * @param inputStream
	 * @return 是否存储成功
	 */
	public boolean save(String path, InputStream inputStream){
		try {
			File file = new File(path);				
			OutputStream outputStream;
		    outputStream = new FileOutputStream(file);
			int read = 0;
			byte[] bytes = new byte[1024];
			while((read = inputStream.read(bytes)) != -1){
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}
}
