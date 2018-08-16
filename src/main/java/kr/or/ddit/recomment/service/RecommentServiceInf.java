package kr.or.ddit.recomment.service;

import java.util.List;

import kr.or.ddit.recomment.model.RecommentVo;

public interface RecommentServiceInf {
	/** 
	 * Method : getRecommentList
	 * 최초작성일 : 2018. 7. 22. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 번호에 맞는 게시판 댓글 조회
	 */
	List<RecommentVo> getRecommentList(int pt_no);
	
	/** 
	 * Method : getRecommentList
	 * 최초작성일 : 2018. 7. 22. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 댓글쓴이 닉네임 조회
	 */
	String getRecommentStdId(int re_no);
	
	/** 
	 * Method : getRecommentList
	 * 최초작성일 : 2018. 7. 23. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 댓글 사용 여부
	 */
	int removeRecom(int rn_no);
	
	/** 
	 * Method : getRecommentList
	 * 최초작성일 : 2018. 7. 23. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 댓글 등록
	 */
	int insertRecomment(RecommentVo rv);
	
}
