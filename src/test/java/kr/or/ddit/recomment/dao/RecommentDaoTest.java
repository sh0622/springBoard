package kr.or.ddit.recomment.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.post.dao.PostDaoInf;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.recomment.model.RecommentVo;

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
public class RecommentDaoTest {

	private RecommentVo rv;
	
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
		
		rv = new RecommentVo();
		
		rv.setId(1);
		rv.setPt_no(1);
		rv.setRe_contnet("댓글 테스트");
		rv.setRe_date(new Date());
		rv.setRe_no(1);
		rv.setRe_remove(0);
		rv.setStd_id("song");
	}
	
	@Resource(name="recommentDao")
	private RecommentDaoInf recommentDao;
	
	@Test
	public void getRecommentList(){
		/***Given***/
	
		/***When***/
		List<RecommentVo> recommentList = recommentDao.getRecommentList(1);

		/***Then***/
		assertEquals(1, recommentList.size());
	}
	
	@Test
	public void removeRecom(){
		/***Given***/
		
		
		/***When***/
		int ck = recommentDao.removeRecom(rv.getRe_no());

		/***Then***/
		assertEquals(1, ck);

	}
	
	@Test
	public void insertRecomment(){
		/***Given***/
		rv = new RecommentVo();
		
		rv.setId(1);
		rv.setPt_no(1);
		rv.setRe_contnet("댓글 테스트");
		rv.setRe_date(new Date());
		rv.setRe_no(2);
		rv.setRe_remove(0);
		rv.setStd_id("song");
		
		/***When***/
		int ck = recommentDao.insertRecomment(rv);

		/***Then***/
		assertEquals(ck, 1);

	}
	
}
