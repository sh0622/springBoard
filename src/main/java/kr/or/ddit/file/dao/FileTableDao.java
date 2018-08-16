package kr.or.ddit.file.dao;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.file.model.FileVo;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("fileTableDao")
public class FileTableDao implements FIleTableDaoInf{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	//private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	/** 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 22.
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시물 번호에 맞는 파일리스트 반환
	 */
	@Override
	public List<FileVo> getFileList(int pt_no) {
		List<FileVo> fileList = template.selectList("file.getFileList", pt_no);
		
		return fileList;
	}

	@Override
	public int insertFile(FileVo fv) {
		int insertCk  = template.insert("file.insertFile", fv);
		
		return insertCk;
	}

	@Override
	public int getFileCnt(int pt_no) {
		int fileCnt  = template.selectOne("file.getFileCnt", pt_no);
		
		return fileCnt;
	}
	
	
}
