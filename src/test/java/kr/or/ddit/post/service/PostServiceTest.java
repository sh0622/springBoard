package kr.or.ddit.post.service;

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
public class PostServiceTest {

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

	@Resource(name="postService")
	private PostServiceInf postService;
	
	@Test
	public void getPostListTest(){
		/***Given***/
		
		/***When***/
		List<PostVo> postList = postService.getPostList(1);

		/***Then***/
		assertEquals(1, postList.get(0).getId());
	}
	
	@Test
	public void getPostPageList(){
		/***Given***/
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("bd_no", 1);
		map.put("page", 1);
		map.put("pageSize", 10);
		
		/***When***/
		Map<String, Object> resultMap = postService.getPostPageList(map);
		List<PostVo> resultList = (List<PostVo>) resultMap.get("postPageList");
		
		/***Then***/
		assertEquals(1, resultList.size());
	}
	
	@Test
	public void insertPost(){
		/***Given***/
		
		/***When***/
		int ck = postService.insertPost(pv);

		/***Then***/
		assertEquals(1, ck);
	}
	
	@Test
	public void getPostDetailList(){
		/***Given***/
		int pt_no = 1;
		
		/***When***/
		PostVo postvo = postService.getPostDetailList(pt_no); 
		
		/***Then***/
		assertEquals(1, postvo.getPt_no());
	}
	
	@Test
	public void removePost(){
		/***Given***/
		
		/***When***/
		int ck = postService.removePost(pv);

		/***Then***/
		assertEquals(1, ck);
	}
	
	@Test
	public void postUpdate(){
		/***Given***/
		pv.setPt_title("수정된 제목");
		pv.setPt_content("수정된 내용");
		
		/***When***/
		int ck = postService.postUpdate(pv);

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
		int ck = postService.postChild(pv);

		/***Then***/
		assertEquals(1, ck);
	}
	
}
