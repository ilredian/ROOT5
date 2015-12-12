package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import file.BoardService;

@Controller
public class TestController {
	
	@Autowired(required=false)
	private BoardService boardService;
	
	/* 파일 업로드 화면 이동 */
	@RequestMapping(value="/fileUpload.go", method=RequestMethod.GET)
	public ModelAndView fileUploadForm() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board.fileUpload_submit");
		
		return mav;
	}
	
	/* 파일 업로드 처리 */
	@RequestMapping(value="/fileUpload.go", method=RequestMethod.POST)
	public ModelAndView fileUpload(MultipartHttpServletRequest mRequest) {
		
		ModelAndView mav = new ModelAndView();
		
		if(boardService.fileUpload(mRequest)) {
			mav.addObject("result", "SUCCESS");
		} else {
			mav.addObject("result", "FAIL");
		}
		
		mav.setViewName("board.fileUpload_result");
		
		return mav;
	}
	
	/* 파일 업로드 화면 이동 */
	@RequestMapping(value="fileUploadAjax.go", method=RequestMethod.GET)
	public ModelAndView fileUploadAjaxForm() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board.fileUpload_ajax");
		
		return mav;
	}
	
	/* 파일 업로드 처리 */
	@RequestMapping(value="fileUploadAjax.go", method=RequestMethod.POST)
	public ModelAndView fileUploadAjax(MultipartHttpServletRequest mRequest) {
		//System.out.println("dd");
		ModelAndView mav = new ModelAndView();
		System.out.println("1");
		if(boardService.fileUpload(mRequest)) {
			mav.addObject("result", "success");
			System.out.println("2");
		} else {
			mav.addObject("result", "fail");
			System.out.println("3");
		}
		mav.setViewName("jsonView");
		return mav;
	}
	
}





