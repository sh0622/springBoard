package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVo;

public interface PostDaoInf {
	
	/** 
	 * Method : getPostList
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 번호에 맞는 게시판 글 조회
	 */
	List<PostVo> getPostList(int bd_no);
	
	/** 
	 * Method : getPostPageList
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param map
	 * @return 
	 * Method 설명 : 게시판 글 페이지네이션한 리스트 반환
	 */
	List<PostVo> getPostPageList(Map<String, Integer> map);
	
	/** 
	 * Method : getPostTotCnt
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 글 전체 건수 반환
	 */
	int getPostTotCnt(int bd_no);
	
	/** 
	 * Method : getPostDetailList
	 * 최초작성일 : 2018. 7. 20. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param pt_no
	 * @return 
	 * Method 설명 : 게시글번호에 맞는 게시글정보 반환 
	 */
	PostVo getPostDetailList(int pt_no);
	
	/** 
	 * Method : insertPost
	 * 최초작성일 : 2018. 7. 22. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param 
	 * @return 
	 * Method 설명 : 게시글 등록 
	 */
	int insertPost(PostVo postVo);
	
	/** 
	 * Method : removePost
	 * 최초작성일 : 2018. 7. 22. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param 
	 * @return 
	 * Method 설명 : 게시물 삭제여부 변경
	 */
	int removePost(PostVo postVo);
	
	/** 
	 * Method : removePost
	 * 최초작성일 : 2018. 7. 23. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param 
	 * @return 
	 * Method 설명 : 게시물 수정
	 */
	int postUpdate(PostVo postVo);
	
	/** 
	 * Method : removePost
	 * 최초작성일 : 2018. 7. 23. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param 
	 * @return 
	 * Method 설명 : 답글 추가
	 */
	int postChild(PostVo postVo);
	
}
