package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.board.model.BoardVo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/root-context.xml",
		"classpath:kr/or/ddit/config/spring/datasource_dev.xml",
		"classpath:kr/or/ddit/config/spring/transaction.xml"})
public class BoardDaoTest {

	private BoardVo boardvo;
	
	@Before
	public void setup() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		datasource.setUsername("jspTest");
		datasource.setPassword("jspTest");
		
		//populator 생성
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("kr/or/ddit/config/db/init.sql"));
		DatabasePopulatorUtils.execute(populator, datasource);
		
		boardvo = new BoardVo();
		boardvo.setId(1);
		boardvo.setBd_no(1);
		boardvo.setBd_name("자유게시판");
		boardvo.setBd_date(new Date());
		boardvo.setBd_ck(1);
		
	}
	
	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	@Test
	public void getMemBoardList(){
		/***Given***/
		
		/***When***/
		List<BoardVo> boardList = boardDao.getMemBoardList();

		/***Then***/
		assertEquals("자유게시판", boardList.get(0).getBd_name());
	}
	
	@Test
	public void insertBoardTest(){
		/***Given***/
		boardvo = new BoardVo();
		boardvo.setId(1);
		boardvo.setBd_no(2);
		boardvo.setBd_name("문의게시판");
		boardvo.setBd_date(new Date());
		boardvo.setBd_ck(1);
		
		/***When***/
		int intck = boardDao.insertBoard(boardvo);

		/***Then***/
		assertEquals(1, intck);
		
	}
	
	@Test
	public void offBoardTest(){
		/***Given***/
		boardvo.setBd_ck(0);
		
		/***When***/
		int bd_ck = boardDao.offBoard(boardvo);

		/***Then***/
		assertEquals(1, bd_ck);
		
	}
	
	@Test
	public void getBdNoVo(){
		/***Given***/
		
		/***When***/
		BoardVo bv = boardDao.getBdNoVo(boardvo.getBd_no());

		/***Then***/
		assertEquals("자유게시판", bv.getBd_name());

	}
}
