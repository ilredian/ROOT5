package controllers;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import DAO.CheaterDAO;
import DTO.CheaterDTO;
import DTO.chartDTO;
import DTO.chartItemsDTO;
import common.ExcelView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ExcelController {
	
	
	@Autowired
	private SqlSession sqlSession;

    // 데이터를 엑셀로 추출하여 프론트엔드에 전달한다.
    @RequestMapping(value = "/excel.go", method = RequestMethod.GET)
    public View viewExcel(
    		@RequestParam(value="sy", required=false, defaultValue="0") int sy,
			@RequestParam(value="sm", required=false, defaultValue="0") int sm,
			@RequestParam(value="ey", required=false, defaultValue="0") int ey,
			@RequestParam(value="em", required=false, defaultValue="0") int em,
    		Model model, 
    		HttpServletResponse response
    		) throws Exception {
    	//우리 DB 정보를 넣어보자
		System.out.println(sy+"/"+sm+"/"+ey+"/"+em);
		//시간 값 설정			
				Date date = new Date();
				int start_year = 0;
				int start_month = 0;
				int end_year = 0;
				int end_month = 0;
				
				int standard_year = date.getYear()+1900;
				model.addAttribute("standard_year", standard_year);
				
				if(sy == 0 || sm == 0 || ey == 0 || em == 0){
					start_year = date.getYear()+1900-5;
					start_month = date.getMonth()+1;
					end_year = date.getYear()+1900;
					end_month = date.getMonth()+1;
				}else{
					start_year = sy;
					start_month = sm;
					end_year = ey;
					end_month = em;
				}
		
	CheaterDAO cheaterDAO = sqlSession.getMapper(CheaterDAO.class);
	int size =0;
	
	//통계 값 가져오기
			if(start_year == end_year){
				size = (end_month - start_month)+1;
			}else if(start_year < end_year){
				size = ((end_month+12*(end_year-start_year)) - start_month)+1;
			}else{
				System.out.println("헤헷");
			}
			System.out.println("size :"+size);
			List<chartDTO> regdate = new ArrayList<chartDTO>();
			if(start_year == end_year){
				for(int i=0; i<size; i++){
					chartDTO chartdto = new chartDTO();
					chartdto.setRegdate(end_year+"-"+(end_month-i));
					regdate.add(chartdto);
				}
			}else{
				int z=0;
				for(int y=0; y<(size)/12+1; y++){
					for(int i=z; i<size; i++){
						if((end_month+12*(y))-i <= 0){
							z=i;
							break;
						}else{
							chartDTO chartdto = new chartDTO();
							chartdto.setRegdate((end_year-y)+"-"+((end_month+12*(y))-i));
							regdate.add(chartdto);
						}
					}
				}
			}
			List<chartDTO> list = new ArrayList<chartDTO>();
			if(size==1){
				String start = regdate.get(0).getRegdate() + "-00";
				String end = regdate.get(0).getRegdate() + "-32";
				System.out.println("start :" +start);
				System.out.println("end :" +end);
				List<chartDTO> chartdto = cheaterDAO.getChartMonth(start, end);
				for(int i=0; i < chartdto.size(); i++){
					list.add(chartdto.get(i));
				}
			}else{
				for(int i=size-1; i>=0; i--){
					chartDTO chartdto = cheaterDAO.getChart(regdate.get(i));
					chartdto.setRegdate(regdate.get(i).getRegdate());
					list.add(chartdto);
				}
			}
			
			// 피해사례 수
			String allCheaterCount = String.format("%,d",
					cheaterDAO.getAllCheaterCount(regdate.get(size - 1).getRegdate(), regdate.get(0).getRegdate()));
			// 카테고리 별 피해 사례 수
			System.out.println("start:" + regdate.get(size - 1).getRegdate() + "end:" + regdate.get(0).getRegdate());
			int countTrade = cheaterDAO.getCountCategory(regdate.get(size - 1).getRegdate(), regdate.get(0).getRegdate(),
					1);
			int countGame = cheaterDAO.getCountCategory(regdate.get(size - 1).getRegdate(), regdate.get(0).getRegdate(), 2);
			int countManner = cheaterDAO.getCountCategory(regdate.get(size - 1).getRegdate(), regdate.get(0).getRegdate(),
					3);
			// 휴대폰 번호 수
			String countPhone = String.format("%,d",
					cheaterDAO.getCountPhone(regdate.get(size - 1).getRegdate(), regdate.get(0).getRegdate()));
			// 계좌번호 수
			String countAccount = String.format("%,d",
					cheaterDAO.getCountAccount(regdate.get(size - 1).getRegdate(), regdate.get(0).getRegdate()));
			// 피해금액
			String countSum = String.format("%,d",
					cheaterDAO.getCountSum(regdate.get(size - 1).getRegdate(), regdate.get(0).getRegdate()));
			// 피해물품 TOP 10
			List<chartItemsDTO> countItems = new ArrayList<chartItemsDTO>();
			chartItemsDTO chartItemsdto = new chartItemsDTO();
			chartItemsdto.setGoodskind("Task");
			chartItemsdto.setCount("'Hours per Day'");
			countItems.add(chartItemsdto);
			List<chartItemsDTO> countItemsTemp = cheaterDAO.getCountItems(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());
			for (int i = 0; i < countItemsTemp.size(); i++) {
				countItems.add(countItemsTemp.get(i));
			}
			// 용의자
			List<CheaterDTO> countCheaterName = cheaterDAO.getCountCheaterName(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());
			// 사이트
			List<CheaterDTO> countDomain = cheaterDAO.getCountDomain(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());
			// 은행
			List<CheaterDTO> countBankName = cheaterDAO.getCountBankName(regdate.get(size - 1).getRegdate(),
					regdate.get(0).getRegdate());

			// 꺾은선 그래프 차트용
			model.addAttribute("list", list);

			// 차트 값 model 객체에 담기
	      System.out.println("데이터담기");
			model.addAttribute("allCheaterCount", allCheaterCount);
			model.addAttribute("countPhone", countPhone);
			model.addAttribute("countAccount", countAccount);
			model.addAttribute("countSum", countSum);
			model.addAttribute("countItems", countItems);
			model.addAttribute("countCheaterName", countCheaterName);
			model.addAttribute("countDomain", countDomain);
			model.addAttribute("countBankName", countBankName);
			model.addAttribute("countTrade", countTrade);
			model.addAttribute("countGame", countGame);
			model.addAttribute("countManner", countManner);

			model.addAttribute("start_year", start_year);
			model.addAttribute("start_month", start_month);
			model.addAttribute("end_year", end_year);
			model.addAttribute("end_month", end_month);
	
        // 엑셀을 출력한다.
        System.out.println("엑셀 출력");
        
        
        	
        return new ExcelView();
        
        
    }	
}