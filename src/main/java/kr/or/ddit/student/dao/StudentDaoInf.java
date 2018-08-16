package kr.or.ddit.student.dao;

import kr.or.ddit.student.model.StudentVo;

public interface StudentDaoInf {
	
	/** 
	 * Method : getStdId
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param id
	 * @return 
	 * Method 설명 : id에 맞는 회원정보를 반환
	 */
	StudentVo getStdId(String std_id);
	
}
