package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.mybatis.SqlMapSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


@Repository("boardDao")
public class BoardDao implements BoardDaoInf {
//	// session객체를 얻어온다
//	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	

	@Override
	public List<BoardVo> getMemBoardList() {
		List<BoardVo> boardList = template.selectList("board.getMemBoardList");
		return boardList;
	}
	
	@Override
	public int insertBoard(BoardVo boardVo) {
		int insertCk = template.insert("board.insertBoard", boardVo);
		
		return insertCk;
	}

	@Override
	public int offBoard(BoardVo boardVo) {
		int offCk = template.update("board.offBoard", boardVo);
		
		return offCk;
	}

	@Override
	public BoardVo getBdNoVo(int bd_no) {
		BoardVo bd_no_vo = template.selectOne("board.getBdNoVo", bd_no);
		
		return bd_no_vo;
	}
		
		
}
