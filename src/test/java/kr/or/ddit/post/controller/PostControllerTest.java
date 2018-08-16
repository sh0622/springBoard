package kr.or.ddit.post.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.file.model.FileVo;
import kr.or.ddit.file.view.FileUtil;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.recomment.model.RecommentVo;
import kr.or.ddit.student.model.StudentVo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.fileupload.FileUpload;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
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
public class PostControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	private PostVo postVo;
	private FileVo fileVo;
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
	public void postListTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/post/list")
				.param("page", "1")
				.param("pageSize", "10")
				.param("bd_no", "1")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		List<PostVo> postList = (List<PostVo>) mav.getModel().get("postPageList");
		
		/***Then***/
		assertEquals(1, postList.size());
		assertEquals("post/postList", viewName);
	}
	
	@Test
	public void postDetailTest() throws Exception{
		/***Given***/
		StudentVo studentVo = new StudentVo();
		studentVo.setId(1);
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("studentVo", studentVo);
		
		MvcResult result = mvc.perform(get("/post/detail")
				.param("pt_no", "1")
				.param("bd_name", "자유게시판")
				.session(session)).andReturn();
				
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		PostVo pv = (PostVo) mav.getModel().get("postVo");
		
		List<RecommentVo> recommentList = (List<RecommentVo>) mav.getModel().get("recommentList");
		
		/***Then***/
		assertEquals("post/postDetail", viewName);
		assertEquals(1, pv.getBd_no());
		assertEquals(1, studentVo.getId());
		assertEquals(1, recommentList.get(0).getId());
	}
	
	@Test
	public void postAddTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/post/add")
				.param("bd_no", "1")
				.param("bd_name", "자유게시판")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("post/postAdd", viewName);
	}
	
	@Test
	public void postAddPostTest2() throws Exception{
		/***Given***/
		StudentVo studentVo = new StudentVo();
		studentVo.setId(1);
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("studentVo", studentVo);
		
		File file = new File(FileUtil.fileUploadPath + File.separator + "dog.jpg");
		MockMultipartFile files = new MockMultipartFile("upoadFile", new FileInputStream(file));
		
		MvcResult result = mvc.perform(fileUpload("/post/add")
				.file(files)
				.param("bd_no", "1")
				.param("title", "게시글")
				.param("smarteditor", "게시글테스트")
				.param("file_cnt", "0")
				.session(session)).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("redirect:/post/detail?pt_no=" + 2, viewName);
	}
	
	@Test
	public void postChildTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/post/child")
				.param("bd_no", "1")
				.param("pt_no", "2")
				.param("pt_group", "1")
				.param("id", "1")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("post/postChild", viewName);
	}
	
	@Test
	public void postChildTest2() throws Exception{
		/***Given***/
		File file = new File(FileUtil.fileUploadPath + File.separator + "dog.jpg");
		MockMultipartFile files = new MockMultipartFile("upoadFile", new FileInputStream(file));
		
		MvcResult result = mvc.perform(fileUpload("/post/child")
				.file(files)
				.param("id", "1")
				.param("pt_no", "2")
				.param("bd_no", "1")
				.param("pt_group", "1")
				.param("smarteditor", "테스트 글")
				.param("title", "테스트제목")
				.param("file_cnt", "0")).andReturn();
				
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		//List<FileVo> fileList = (List<FileVo>) mav.getModel().get("fileList");

		/***Then***/
		assertEquals("redirect:/post/detail?pt_no=" + 2, viewName);
		//assertEquals(1, fileList.get(0).getFile_no());
	}
	
	@Test
	public void postDeleteTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/post/delete").param("pt_no", "1")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("post/detail", viewName);
	} 
	
	@Test
	public void postDeleteTest2() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(post("/post/delete").param("pt_no", "1")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		int remCn = (int) mav.getModel().get("remCnt");

		/***Then***/
		assertEquals("redirect:/post/list?bd_no=" + 1, viewName);
		assertEquals(1,remCn);
	}
	
	@Test
	public void postUpdateTest() throws Exception{
		/***Given***/
		MvcResult result = mvc.perform(get("/post/update").param("pt_no", "1")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		PostVo postVo = (PostVo) mav.getModel().get("postVo");
		List<FileVo> fileList = (List<FileVo>) mav.getModel().get("fileList");

		/***Then***/
		assertEquals("post/postUpdate", viewName);
		assertEquals(1, postVo.getPt_no());
		assertEquals(1, fileList.get(0).getFile_no());
	}
	
	@Test
	public void postUpdateTest2() throws Exception{
		/***Given***/
		File file = new File(FileUtil.fileUploadPath + File.separator + "dog.jpg");
		MockMultipartFile files = new MockMultipartFile("upoadFile", new FileInputStream(file));
		
		MvcResult result = mvc.perform(fileUpload("/post/update")
				.file(files)
				.param("title", "테스트")
				.param("smarteditor", "테스트입니다")
				.param("bd_no", "1")
				.param("file_cnt", "0")
				.param("pt_no", "1")).andReturn();
		
		/***When***/
		ModelAndView mav = result.getModelAndView();
		String viewName = mav.getViewName();
		
		List<FileVo> fileList = (List<FileVo>) mav.getModel().get("fileList");
		
		/***Then***/
		assertEquals("redirect:/post/detail?pt_no=" + 1, viewName);
		assertEquals(1, fileList.get(0).getFile_no());
	}
	
}
