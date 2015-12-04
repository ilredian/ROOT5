package controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DAO.QueryDAO;
import DTO.QueryDTO;

@Controller
public class AjaxController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "totalSearchAjax.go", method = RequestMethod.POST)
	public ModelAndView totalSearchAjax(
			@RequestParam("totalSearchAjax") String query) throws Exception {

		ModelAndView mav = new ModelAndView();
		QueryDAO queryDAO = sqlSession.getMapper(QueryDAO.class);

		List<QueryDTO> querylist = queryDAO.getSearchQuery(query, 1);
		List<String> list = new ArrayList<String>();
		if(!querylist.isEmpty()){
			for(int i=0; i<querylist.size(); i++){
				list.add(querylist.get(i).getQuery());
			}
		}
		mav.addObject("auto", list);
		mav.setViewName("jsonView");
		return mav;
	}
}
