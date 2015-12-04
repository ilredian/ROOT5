package controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import DAO.CheatDAO;
import DTO.CheatDTO;

@Controller
public class acontroller {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="ajax.go", method=RequestMethod.GET)
	public String bb(){
		return "jsp.index";
	}

   @RequestMapping(value="ajax.go", method=RequestMethod.POST)
   public ModelAndView aa(
		   @RequestParam("joinOk") String joinOk
		   ) throws Exception{
      
      System.out.println("컨트롤러 타는지 확인");
      System.out.println(joinOk);
      
      ModelAndView mav= new ModelAndView();
      
      CheatDAO dao = sqlSession.getMapper(CheatDAO.class);
      List<CheatDTO> list = dao.getAllCheat();
      /*SocialPerson person = dao.getPerson(id);*/
      mav.addObject("list",list);
      mav.setViewName("jsonView");
      return mav;
      
      
/*      List<String> list = new ArrayList<String>();
         list.add("'ActionScript'");
         list.add("'AppleScript'");
         list.add("'BASIC'");
         list.add("'C'");
         list.add("'C++'");
         list.add("'Clojure'");
         list.add("'COBOL'");
         
         model.addAttribute("list",list.toString());
         System.out.println(list.toString());
      return "jsp.index";*/
   }
}