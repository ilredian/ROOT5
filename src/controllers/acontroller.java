package controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

@Controller
public class acontroller {

   @RequestMapping(value="jsp.go", method=RequestMethod.GET)
   public String aa(Model model) throws Exception{
      
      System.out.println("컨트롤러 타는지 확인");
      
      
      List<String> list = new ArrayList<String>();
         list.add("'ActionScript'");
         list.add("'AppleScript'");
         list.add("'BASIC'");
         list.add("'C'");
         list.add("'C++'");
         list.add("'Clojure'");
         list.add("'COBOL'");
         
         model.addAttribute("list",list.toString());
         System.out.println(list.toString());
      return "jsp.index";
   }
}