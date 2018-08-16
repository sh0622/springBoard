package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.board.dao.BoardDaoInf;
import kr.or.ddit.board.model.BoardVo;

import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardService implements BoardServiceInf {
	
	@Resource(name="boardDao")
	private BoardDaoInf boardDao;
	
	@Override
	public List<BoardVo> getMemBoardList() {
		return boardDao.getMemBoardList();
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return boardDao.insertBoard(boardVo);
	}

	@Override
	public int offBoard(BoardVo boardVo) {
		return boardDao.offBoard(boardVo);
	}

	@Override
	public BoardVo getBdNoVo(int bd_no) {
		return boardDao.getBdNoVo(bd_no);
	}

}
