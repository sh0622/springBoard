package kr.or.ddit.board.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	private BoardVo boardvo;
	
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
	public void boardListTest() throws Exception {
		/***Given***/
		MvcResult result = mvc.perform(get("/board/list")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		HttpSession session = result.getRequest().getSession();
		List<BoardVo> boardList = (List<BoardVo>)session.getAttribute("boardList");
		
		/***Then***/
		assertEquals("board/boardList", viewName);
		assertEquals(1, boardList.size());
		
	}
	
	@Test
	public void boardUpdateTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/board/update")
				.param("bd_ck", "0")
				.param("bd_name", "수정게시판")
				.param("bd_no", "1"))
				.andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		List<BoardVo> boardList = (List<BoardVo>)mav.getModel().get("boardList");

		/***Then***/
		assertEquals("수정게시판", boardList.get(0).getBd_name());
		assertEquals("board/boardList", viewName);
	}
	
	@Test
	public void boardMakingTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/board/making")
				.param("id", "1")
				.param("bd_name", "워너원")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		List<BoardVo> boardList = (List<BoardVo>)mav.getModel().get("boardList");
		
		/***Then***/
		assertEquals("워너원", boardList.get(1).getBd_name());
		assertEquals("board/boardList", viewName);

	}
}
