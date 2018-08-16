package kr.or.ddit.file.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.file.dao.FIleTableDaoInf;
import kr.or.ddit.file.model.FileVo;

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
public class FileServiceTest {

	private FileVo fv;
	
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
		
		fv = new FileVo();
		fv.setPt_no(1);
		fv.setFile_no(2);
		fv.setFile_name("attachImage_2444970688.jpeg");
		fv.setFile_route("D:\\A_TeachingMaterial\\7.JspSpring\\fileUpload");
		fv.setUpload_name("8b12bf9b-ee90-4ce9-9f42-d65ead26c1f6");
		fv.setFile_date(new Date());
		
	}
	
	@Resource(name="fileTableService")
	public FileTableServiceInf fileService;
	
	@Test
	public void getFileListTest(){
		/***Given***/
		int pt_no = 1;
		
		/***When***/
		List<FileVo> fileList = fileService.getFileList(pt_no);

		/***Then***/
		assertEquals(1, fileList.get(0).getPt_no());
	}
	
	@Test
	public void insertFile(){
		/***Given***/
		
		/***When***/
		int ck = fileService.insertFile(fv);

		/***Then***/
		assertEquals(1, ck);
	}
	
	@Test
	public void getFileCnt(){
		/***Given***/
		
		/***When***/
		int cnt = fileService.getFileCnt(fv.getPt_no());
		
		/***Then***/
		assertEquals(1, cnt);
	}

}
