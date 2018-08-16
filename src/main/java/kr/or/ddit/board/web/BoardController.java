package kr.or.ddit.board.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.student.model.StudentVo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	//게시판 리스트
	@RequestMapping("/list")
	public String boardList(HttpSession session){
		
		//List<BoardVo> boardList = (List<BoardVo>) session.getAttribute("boardList");
		List<BoardVo> boardList = boardService.getMemBoardList();
		session.setAttribute("boardList", boardList);
		
		return "board/boardList";
	}
	
	//게시판 업데이트
	@RequestMapping("/update")
	public String boardUpdate(HttpSession session, BoardVo bodVo, StudentVo stVo, Model model){

		int cnt = boardService.offBoard(bodVo);
		
		List<BoardVo> barodList = boardService.getMemBoardList();
		model.addAttribute("boardList", barodList);
		
		return "board/boardList";
	}
	
	//게시판 생성
	@RequestMapping("/making")
	public String boardMaking( Model model, HttpSession session, BoardVo bodVo, StudentVo stVo){ 
		
		boardService.insertBoard(bodVo);
		List<BoardVo> barodList = boardService.getMemBoardList();
		
		model.addAttribute("boardList", barodList);
		
		return "board/boardList";
	}

}
