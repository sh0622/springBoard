package kr.or.ddit.student.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import kr.or.ddit.student.model.StudentVo;

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
public class StudentServiceTest {

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
	}
	
	@Resource(name="studentService")
	private StudentServiceInf studentService;
	
	@Test
	public void getStdId(){
		/***Given***/
		String std_id = "song";
		
		/***When***/
		StudentVo studentVo = studentService.getStdId(std_id);
		
		/***Then***/
		assertEquals(1, studentVo.getId());

	}

}
