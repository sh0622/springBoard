package kr.or.ddit.post.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.post.model.PostVo;

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
public class PostDaoTest {

	private PostVo pv;

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
		
		pv = new PostVo();
		
		pv.setBd_no(1);
		pv.setPt_no(1);
		pv.setId(1);
		pv.setPt_content("junit테스트");
		pv.setPt_title("junit테스트");
		pv.setPt_remove(0);
		pv.setRn(1);
		pv.setPt_seq(0);
		pv.setPt_group(1);
		pv.setPt_date(new Date());
	}
	
	@Resource(name="postDao")
	private PostDaoInf postDao;
	
	
	@Test
	public void getPostListTest(){
		/***Given***/
		
		/***When***/
		List<PostVo> postList = postDao.getPostList(1);
		
		/***Then***/
		assertEquals(1, postList.get(0).getId());
	}
	
	@Test
	public void getPostPageListTest(){
		/***Given***/
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("bd_no", 1);
		map.put("page", 1);
		map.put("pageSize", 10);
		
		/***When***/
		List<PostVo> getPostPageList = postDao.getPostPageList(map);

		/***Then***/
		assertEquals(1, getPostPageList.size());
	}
	
	@Test
	public void getPostTotCntTest(){
		/***Given***/
		
		/***When***/
		int cnt = postDao.getPostTotCnt(pv.getBd_no());

		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void insertPostTest(){
		/***Given***/
		pv = new PostVo();
		
		pv.setBd_no(1);
		pv.setPt_no(2);
		pv.setId(1);
		pv.setPt_content("junit테스트");
		pv.setPt_title("junit테스트");
		pv.setPt_remove(0);
		pv.setRn(2);
		pv.setPt_seq(0);
		pv.setPt_group(2);
		pv.setPt_date(new Date());
		
		/***When***/
		int ck = postDao.insertPost(pv);

		/***Then***/
		assertEquals(1, ck);
	}
	
	@Test
	public void getPostDetailListTest(){
		/***Given***/
		
		/***When***/
		PostVo postvo = postDao.getPostDetailList(pv.getPt_no()); 

		/***Then***/
		assertEquals(1, postvo.getPt_no());
	}
	
	@Test
	public void removePostTest(){
		/***Given***/
		
		/***When***/
		int ck = postDao.removePost(pv);
		
		/***Then***/
		assertEquals(1, ck);

	}
	
	@Test
	public void postUpdateTest(){
		/***Given***/
		pv.setPt_title("수정된 제목");
		pv.setPt_content("수정된 내용");
		
		/***When***/
		int ck = postDao.postUpdate(pv);

		/***Then***/
		assertEquals(ck, 1);
	}
	
	@Test
	public void postChildTest(){
		/***Given***/
		pv = new PostVo();
		pv.setBd_no(1);
		pv.setPt_no(3);
		pv.setId(1);
		pv.setPt_content("junit테스트");
		pv.setPt_title("junit테스트");
		pv.setPt_remove(0);
		pv.setRn(2);
		pv.setPt_seq(1);
		pv.setPt_group(3);
		pv.setPt_date(new Date());
		
		/***When***/
		int ck = postDao.postChild(pv);

		/***Then***/
		assertEquals(1, ck);
	}
	

}
