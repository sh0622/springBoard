package kr.or.ddit.student.dao;

import javax.annotation.Resource;

import kr.or.ddit.mybatis.SqlMapSessionFactory;
import kr.or.ddit.student.model.StudentVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDao implements StudentDaoInf {
//
//	//session객체를 얻어온다
//	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	
	/** 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 학생 아이디에 맞는 학생의 정보 반환
	 */
	@Override
	public StudentVo getStdId(String std_id) {
		StudentVo student = template.selectOne("student.getStdId", std_id);
		
		return student;
	}

}
