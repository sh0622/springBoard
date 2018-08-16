package kr.or.ddit.recomment.dao;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.recomment.model.RecommentVo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("recommentDao")
public class RecommentDao implements RecommentDaoInf {
	
	//private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	
	@Override
	public List<RecommentVo> getRecommentList(int pt_no) {
		List<RecommentVo> recommentList = template.selectList("recomment.getRecommentList", pt_no);
		
		return recommentList;
	}

	@Override
	public String getRecommentStdId(int re_no) {
		String rv = template.selectOne("recomment.getRecommentStdId", re_no);
		
		return rv;
	}

	@Override
	public int removeRecom(int rn_no) {
		int recCnt= template.update("recomment.removeRecomment", rn_no);
		
		return recCnt;
	}

	@Override
	public int insertRecomment(RecommentVo rv) {
		int insertCnt= template.insert("recomment.insertRecomment", rv);
		
		return insertCnt;
	}
	
	

}
