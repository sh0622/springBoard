package kr.or.ddit.recomment.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.recomment.model.RecommentVo;
import kr.or.ddit.recomment.service.RecommentServiceInf;
import kr.or.ddit.student.model.StudentVo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/recomment")
public class RecommentController {

	@Resource(name="recommentService")
	private RecommentServiceInf recommentService;
	
	//댓글등록
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String recommentAdd(
			PostVo postVo,
			HttpSession session,
			@RequestParam("recomment") String re_content){
		
		StudentVo studentVo = (StudentVo) session.getAttribute("studentVo");
		session.setAttribute("studentVo", studentVo);
		
		RecommentVo rv = new RecommentVo();
		rv.setId(studentVo.getId());
		rv.setPt_no(postVo.getPt_no());
		rv.setRe_contnet(re_content);
		
		recommentService.insertRecomment(rv);
		
		return "redirect:/post/detail?pt_no="+postVo.getPt_no();
	}
	
	//댓글삭제
	@RequestMapping("/remove")
	public String recommentReomve(@RequestParam("rn_no") int rn_no,
			@RequestParam("pt_no") int pt_no){
		
		recommentService.removeRecom(rn_no);
		
		return "redirect:/post/detail?pt_no="+pt_no;
	}
	
	
}
