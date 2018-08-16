package kr.or.ddit.file.dao;

import java.util.List;

import kr.or.ddit.file.model.FileVo;

public interface FIleTableDaoInf{
	
	/** 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 22. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시물 파일리스트 반환
	 */
	List<FileVo> getFileList(int pt_no);
	
	/** 
	 * Method : insertFile
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 파일 저장
	 */
	int insertFile(FileVo fv);
	
	/** 
	 * Method : getFileCnt
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판의 파일 수 반환
	 */
	int getFileCnt(int pt_no);
	
}
