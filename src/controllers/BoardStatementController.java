package controllers;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardStatementController {
	@Autowired
	private SqlSession sqlSession;
	
	////////////진술서 게시판////////////////
	
	//1-1. 게임 메인
	@RequestMapping("gameMain.go")
	public String gameMain(String pg , String f , String q , Model model){
		//게시판 기본 설정(기본값 처리)/////////////
				int page = 1;
				String field = "TITLE";
				String query ="%%";
				//////////////////////////////////////
				if(pg != null && pg.equals("")){
					page = Integer.parseInt(pg);
				}
				if(f != null && f.equals("")){
					field = f;
				}
				if(q != null && q.equals("")){
					query = q;
				}
				System.out.println(page + " / " + field + " / " + query);
			/*	
				BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
				List<BoardFreeDTO> list= boardFreeDAO.getNotices(page, field, query);
				
				model.addAttribute("list", list); //자동 forward
			*/	
		
		return "home.boardStatement.gameMain";
	}
	
	//1-2. 게임 상세
	@RequestMapping("gameView.go")
	public String gameView(String seq , Model model){
		
		/*	
	     BoardStatementDAO boardStatementDAO = sqlSession.getMapper(BoardStatementDAO.class);
		 BoardStatementDTO boardStatementDTO = boardStatementDAO.getNotice(seq);
		 model.addAttribute("boardStatementDTO", boardStatementDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
		 */	
		
		return "home.boardStatement.gameView";
	}
	
	//2-1. 매너 메인
	@RequestMapping("mannerMain.go")
	public String mannerMain(String pg , String f , String q , Model model){
			//게시판 기본 설정(기본값 처리)/////////////
					int page = 1;
					String field = "TITLE";
					String query ="%%";
					//////////////////////////////////////
					if(pg != null && pg.equals("")){
						page = Integer.parseInt(pg);
					}
					if(f != null && f.equals("")){
						field = f;
					}
					if(q != null && q.equals("")){
						query = q;
					}
					System.out.println(page + " / " + field + " / " + query);
				/*	
					BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
					List<BoardFreeDTO> list= boardFreeDAO.getNotices(page, field, query);
					
					model.addAttribute("list", list); //자동 forward
				*/	
			
		
		return "home.boardStatement.mannerMain";
	}
	
	//2-2. 매너 상세
	@RequestMapping("mannerView.go")
	public String mannerView(String seq , Model model){
	
		/*	
	     BoardStatementDAO boardStatementDAO = sqlSession.getMapper(BoardStatementDAO.class);
		 BoardStatementDTO boardStatementDTO = boardStatementDAO.getNotice(seq);
		 model.addAttribute("boardStatementDTO", boardStatementDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
		 */	

		return "home.boardStatement.mannerView";
	}
	
	//3-1 스팸 메인
	@RequestMapping("spamMain.go")
	public String spamMain(String pg , String f , String q , Model model){
			//게시판 기본 설정(기본값 처리)/////////////
					int page = 1;
					String field = "TITLE";
					String query ="%%";
					//////////////////////////////////////
					if(pg != null && pg.equals("")){
						page = Integer.parseInt(pg);
					}
					if(f != null && f.equals("")){
						field = f;
					}
					if(q != null && q.equals("")){
						query = q;
					}
					System.out.println(page + " / " + field + " / " + query);
				/*	
					BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
					List<BoardFreeDTO> list= boardFreeDAO.getNotices(page, field, query);
					
					model.addAttribute("list", list); //자동 forward
				*/	
			
		
		
		return "home.boardStatement.spamMain";
	}
	
	//3-2 스팸 상세
	@RequestMapping("spamView.go")
	public String spamView(String seq , Model model){
	
		/*	
	     BoardStatementDAO boardStatementDAO = sqlSession.getMapper(BoardStatementDAO.class);
		 BoardStatementDTO boardStatementDTO = boardStatementDAO.getNotice(seq);
		 model.addAttribute("boardStatementDTO", boardStatementDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
		 */	

		
		return "home.boardStatement.spamView";
	}
	
	//4-1. 직거래 메인
	@RequestMapping("tradeMain.go")
		public String tradeMain(String pg , String f , String q , Model model){
			//게시판 기본 설정(기본값 처리)/////////////
					int page = 1;
					String field = "TITLE";
					String query ="%%";
					//////////////////////////////////////
					if(pg != null && pg.equals("")){
						page = Integer.parseInt(pg);
					}
					if(f != null && f.equals("")){
						field = f;
					}
					if(q != null && q.equals("")){
						query = q;
					}
					System.out.println(page + " / " + field + " / " + query);
				/*	
					BoardFreeDAO boardFreeDAO = sqlSession.getMapper(BoardFreeDAO.class);
					List<BoardFreeDTO> list= boardFreeDAO.getNotices(page, field, query);
					
					model.addAttribute("list", list); //자동 forward
				*/	
		
		return "home.boardStatement.tradeMain";
	}
	
	//4-2. 직거래 상세
	@RequestMapping("tradeView.go")
	public String tradeView(String seq , Model model){
	
		
		/*	
	     BoardStatementDAO boardStatementDAO = sqlSession.getMapper(BoardStatementDAO.class);
		 BoardStatementDTO boardStatementDTO = boardStatementDAO.getNotice(seq);
		 model.addAttribute("boardStatementDTO", boardStatementDTO); ///// DB 테이블 명--파라미터명 일치 여부 확인후 수정바람*****
		 */	

		
		return "home.boardStatement.tradeView";
	}
}
