package kr.or.ddit.student.service;

import javax.annotation.Resource;

import kr.or.ddit.student.dao.StudentDaoInf;
import kr.or.ddit.student.model.StudentVo;

import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentService implements StudentServiceInf {

	@Resource(name="studentDao")
	private StudentDaoInf studentDao;
	
	/** 
	 * Method : getStdId
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param id
	 * @return 
	 * Method 설명 : id에 맞는 회원정보를 반환
	 */
	@Override
	public StudentVo getStdId(String std_id) {
		return studentDao.getStdId(std_id);
	}


}
