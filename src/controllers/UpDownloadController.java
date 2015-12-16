package controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import service.UploadDTO;
import service.UploadService;

@Controller
public class UpDownloadController implements ApplicationContextAware {

	@Autowired
	private UploadService uploadService;
	
    private WebApplicationContext context = null;
    
    /* 파일 업로드 처리 */
	@RequestMapping(value="fileUploadAjax.go", method=RequestMethod.POST)
	public ModelAndView fileUploadAjax(MultipartHttpServletRequest mRequest) {
		
		ModelAndView mav = new ModelAndView();
		UploadDTO uploadDTO = new UploadDTO();
		uploadDTO = uploadService.fileUpload(mRequest);
		if(uploadDTO.isSuccess()) {
			mav.addObject("result", "success");
			mav.addObject("file",uploadDTO.getFilename());
		} else {
			mav.addObject("result", "fail");
		}
		
		mav.setViewName("jsonView");
		return mav;
	}
     
	/* 파일 다운로드 처리 */
    @RequestMapping("download.go")
    public ModelAndView download(@RequestParam("path")String path, 
                                  @RequestParam("fileName")String fileName){
         
        String fullPath = path + "\\" + fileName;
         
        File file = new File(fullPath);
         
        return new ModelAndView("download", "downloadFile", file);
    }
 
    @Override
    public void setApplicationContext(ApplicationContext arg0) {
        // TODO Auto-generated method stub
        this.context = (WebApplicationContext)arg0;
    }
}
