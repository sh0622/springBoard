package kr.or.ddit.student.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.student.model.StudentVo;
import kr.or.ddit.student.service.StudentServiceInf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
								"classpath:kr/or/ddit/config/spring/datasource_dev.xml",
								"classpath:kr/or/ddit/config/spring/transaction.xml",
								"classpath:kr/or/ddit/config/spring/servlet-context.xml"})
@WebAppConfiguration
public class StudentControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}	
	
	@Test
	public void loginTest() throws Exception {
		/***Given***/
		MvcResult result = mvc.perform(post("/login/loginProcess")
				.param("std_id", "song")
				.param("pw", "1234"))
				.andReturn();
				
		/***When***/
		ModelAndView mav = result.getModelAndView();
		
		String viewName = mav.getViewName();
		
		HttpSession session = result.getRequest().getSession();
		StudentVo studentVo = (StudentVo) session.getAttribute("studentVo");
		List<BoardVo> boardList = (List<BoardVo>) session.getAttribute("boardList");
		
		/***Then***/
		assertEquals("한송희", studentVo.getName());
		assertEquals(1, boardList.size());
		assertEquals("main", viewName);
	}
}
