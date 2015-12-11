package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class introductionController {

		@RequestMapping("introduction.go")
		public String introduction(){
			return "introduction.introduction";
			
		}
		@RequestMapping("introductionhistory.go")
		public String introductionhistory(){
			return "introduction.introductionhistory";
			
		}
		@RequestMapping("introductiondb.go")
		public String introductiondb(){
			return "introduction.introductiondb";
			
		}
		@RequestMapping("introductionservice.go")
		public String introductionservice(){
			return "introduction.introductionservice";
			
		}
}
