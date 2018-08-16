package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.post.model.PostVo;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("postDao")
public class PostDao implements PostDaoInf {
	// session객체를 얻어온다
	//private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	/**
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 18.
	 * 작성자 : PC12
	 * 변경이력 :
	 * 
	 * @return Method 설명 : 번호에 맞는 게시판 글 조회
	 */
	@Override
	public List<PostVo> getPostList(int bd_no) {
		List<PostVo> boardList = template.selectList("post.getPostList", bd_no);

		return boardList;
	}

	/**
	 * Method : getPostPageList
	 * 최초작성일 : 2018. 7. 18.
	 * 작성자 : PC12
	 * 변경이력 :
	 * 
	 * @param map
	 * @return Method 설명 : 게시판 글 페이지네이션한 리스트 반환
	 */
	@Override
	public List<PostVo> getPostPageList(Map<String, Integer> map) {
		List<PostVo> postList = template.selectList("post.getPostPageList", map);

		return postList;
	}

	/**
	 * Method : getPostTotCnt
	 * 최초작성일 : 2018. 7. 18.
	 * 작성자 : PC12
	 * 변경이력 :
	 * 
	 * @return Method 설명 : 게시판 글 전체 건수 반환
	 */
	@Override
	public int getPostTotCnt(int bd_no) {
		int totCnt = template.selectOne("post.getPostTotCnt", bd_no);

		return totCnt;
	}
	
	/** 
	 * Method : getPostDetailList
	 * 최초작성일 : 2018. 7. 20. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param pt_no
	 * @return 
	 * Method 설명 : 게시글번호에 맞는 게시글정보 반환 
	 */
	public PostVo getPostDetailList(int pt_no){
		PostVo postvo = template.selectOne("post.getPostDetailList", pt_no);

		return postvo;
	}

	@Override
	public int insertPost(PostVo postVo) {
		int intsertCnt = template.insert("post.insertPost", postVo);
		
		return intsertCnt;
	}

	@Override
	public int removePost(PostVo postVo) {
		int removeCnt = template.update("post.removePost", postVo);
		
		return removeCnt;
	}

	@Override
	public int postUpdate(PostVo postVo) {
		int updateCnt = template.update("post.postUpdate", postVo);
		
		return updateCnt;
	}

	@Override
	public int postChild(PostVo postVo) {
		int insertChildCnt = template.insert("post.postChild", postVo);
		
		return insertChildCnt;
	}
	

}
