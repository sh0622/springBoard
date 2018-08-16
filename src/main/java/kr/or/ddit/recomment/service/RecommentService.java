package kr.or.ddit.recomment.service;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.recomment.dao.RecommentDaoInf;
import kr.or.ddit.recomment.model.RecommentVo;

import org.springframework.stereotype.Service;

@Service("recommentService")
public class RecommentService implements RecommentServiceInf {

	@Resource(name="recommentDao")
	private RecommentDaoInf recommentDao;
	
	@Override
	public List<RecommentVo> getRecommentList(int pt_no) {
		return recommentDao.getRecommentList(pt_no);
	}

	@Override
	public String getRecommentStdId(int re_no) {
		return recommentDao.getRecommentStdId(re_no);
	}

	@Override
	public int removeRecom(int rn_no) {
		return recommentDao.removeRecom(rn_no);
	}

	@Override
	public int insertRecomment(RecommentVo rv) {
		return recommentDao.insertRecomment(rv);
	}
	
}
