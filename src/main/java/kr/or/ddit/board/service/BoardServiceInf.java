package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

public interface BoardServiceInf {
	
	/** 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 반환
	 */
	List<BoardVo> getMemBoardList();
	
	/** 
	 * Method : insertBoard
	 * 최초작성일 : 2018. 7. 19. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param bd_name
	 * @return 
	 * Method 설명 : 게시판 생성
	 */
	int insertBoard(BoardVo boardVo);
	
	/** 
	 * Method : offBoard
	 * 최초작성일 : 2018. 7. 19. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param bd_id
	 * @return 
	 * Method 설명 : 게시판 비활성화 
	 */
	int offBoard(BoardVo boardVo);

	
	
	/** 
	 * Method : getBdNoVo
	 * 최초작성일 : 2018. 7. 22. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param bd_no
	 * @return 
	 * Method 설명 : 게시판 번호에 맞는 리스트 반환
	 */
	BoardVo getBdNoVo(int bd_no);
	
}
