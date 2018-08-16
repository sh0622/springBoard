package kr.or.ddit.recomment.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import kr.or.ddit.file.model.FileVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.recomment.model.RecommentVo;
import kr.or.ddit.student.model.StudentVo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mock.web.MockHttpSession;
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
public class RecommentControllerTest {
	

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	private RecommentVo recommentVo;
	
	@Before
	public void setup(){
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		datasource.setUsername("jspTest");
		datasource.setPassword("jspTest");
		
		//populator 생성
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("kr/or/ddit/config/db/init.sql"));
		DatabasePopulatorUtils.execute(populator, datasource);
		
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}	
	
	
	@Test
	public void recommentAddTest() throws Exception {
		/***Given***/
		StudentVo studentVo = new StudentVo();
		studentVo.setId(1);
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("studentVo", studentVo);
		
		MvcResult result = mvc.perform(post("/recomment/add")
				.param("recomment", "댓글테스트")
				.param("pt_no", "1")
				.session(session)).andReturn();
				
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("redirect:/post/detail?pt_no="+1, viewName);
	}
	
	@Test
	public void recommentReomveTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/recomment/remove")
				.param("rn_no", "2")
				.param("pt_no", "1")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("redirect:/post/detail?pt_no="+1, viewName);

	}

}
