package service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class UploadService {

	public UploadDTO fileUpload(MultipartHttpServletRequest mRequest) {

		UploadDTO uploadDTO = new UploadDTO();
		uploadDTO.setSuccess(false);
		
		String uploadPath = "/opt/tomcat/webapps/ROOT/upload/";
		
		File dir = new File(uploadPath);

		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		Iterator<String> iter = mRequest.getFileNames();
		while(iter.hasNext()) {
			String uploadFileName = iter.next();
			
			MultipartFile mFile = mRequest.getFile(uploadFileName);
			String originalFileName = mFile.getOriginalFilename();
			String saveFileName = originalFileName;
			
			if(saveFileName != null && !saveFileName.equals("")) {
				if(new File(uploadPath + saveFileName).exists()) {
					saveFileName = saveFileName + "_" + System.currentTimeMillis();
				}
				
				try {
					mFile.transferTo(new File(uploadPath + saveFileName));
					String filename = uploadPath + saveFileName;
					uploadDTO.setSuccess(true);
					int saveFileLength = saveFileName.length();
					int saveUploadPath = uploadPath.indexOf("/upload/");
					uploadDTO.setFilename(filename.substring(saveUploadPath, saveUploadPath+saveFileLength+8));
					uploadDTO.setFullfilename(filename);
				} catch (IllegalStateException e) {
					e.printStackTrace();
					uploadDTO.setSuccess(false);
				} catch (IOException e) {
					e.printStackTrace();
					uploadDTO.setSuccess(false);
				}
			} // if end
		} // while end
		return uploadDTO;
	} // fileUpload end

}