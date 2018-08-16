package kr.or.ddit.student.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.student.model.StudentVo;
import kr.or.ddit.student.service.StudentServiceInf;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@RequestMapping("/login")
@Controller
public class LoginController extends HandlerInterceptorAdapter{
	
	@Resource(name="studentService")
	private StudentServiceInf studentService;
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	
	@RequestMapping("/view")
	public String login(){
		return "/login/login";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "/main";
	}
	
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public String loginView(@RequestParam("std_id") String std_id,
			@RequestParam("pw") String pw,
			HttpSession session) { 

		String pwKisa = KISA_SHA256.encrypt(pw);
		StudentVo studnetVo = studentService.getStdId(std_id);
		List<BoardVo> boardList = boardService.getMemBoardList();
		String returnUrl = "";
		if(studnetVo != null && studnetVo.validateUser(std_id, pwKisa)){
			session.setAttribute("studentVo", studnetVo);
			session.setAttribute("boardList", boardList);
			
			returnUrl = "main";
		}else{
			returnUrl = "/login/login";
		}
		
		return returnUrl;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session){
		session.invalidate();
		return "/login/login";
	}
			
	

}
