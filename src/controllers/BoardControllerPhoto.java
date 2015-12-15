package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardControllerPhoto {
   
   @RequestMapping(value="PhotoMain.go", method = RequestMethod.GET)
   public String freeMain(){
      
      System.out.println("사진게시판 이동");
      
      return "home.boardPhoto.photoMain";
   }
   @RequestMapping(value="PhotoWrite.go", method = RequestMethod.GET)
   public String photowrite(){
      
      System.out.println("사진글 등록");
      
      return "home.boardPhoto.photowrite";
   }
}