package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChartController {

	@RequestMapping(value="chartMain.go", method=RequestMethod.GET)
	public String chartMain(){
		
		//로그남기기
		System.out.println("피해사례 통계 메인으로 이동");
		
		return "chart.chartMain";
	}

}
