package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class introductionController {

		@RequestMapping("introduction.go")
		public String introduction(){
			return "contact.introduction";
			
		}
		@RequestMapping("introductionhistory.go")
		public String introductionhistory(){
			return "contact.introductionhistory";
			
		}
		@RequestMapping("introductiondb.go")
		public String introductiondb(){
			return "contact.introductiondb";
			
		}
		@RequestMapping("introductionservice.go")
		public String introductionservice(){
			return "contact.introductionservice";
			
		}
}
