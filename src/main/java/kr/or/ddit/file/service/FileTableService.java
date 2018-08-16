package kr.or.ddit.file.service;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.file.dao.FIleTableDaoInf;
import kr.or.ddit.file.model.FileVo;

import org.springframework.stereotype.Service;

@Service("fileTableService")
public class FileTableService implements FileTableServiceInf {

	@Resource(name="fileTableDao")
	private FIleTableDaoInf fileTableDao;
	
	@Override
	public List<FileVo> getFileList(int pt_no) {
		return fileTableDao.getFileList(pt_no);
	}

	@Override
	public int insertFile(FileVo fv) {
		return fileTableDao.insertFile(fv);
	}

	@Override
	public int getFileCnt(int pt_no) {
		return fileTableDao.getFileCnt(pt_no);
	}

}
